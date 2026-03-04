package com.tapflowfeature_nfc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapflow.NfcTagResult
import com.tapflow.model.NfcReadHistory
import com.tapflow.usecase.ObserveNfcHistoryUseCase
import com.tapflow.usecase.HandleNfcTagUseCase
import com.tapflow.usecase.RegisterNfcReadUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NfcViewModel(
    private val handleNfcTagUseCase: HandleNfcTagUseCase,
    private val registerNfcReadUseCase: RegisterNfcReadUseCase,
    private val observeNfcHistoryUseCase: ObserveNfcHistoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<NfcUiState>(NfcUiState.Idle)
    val uiState: StateFlow<NfcUiState> = _uiState

    val history: StateFlow<List<NfcReadHistory>> =
        observeNfcHistoryUseCase.execute()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun onNfcTag(uid: String) {
        viewModelScope.launch {
            _uiState.value = NfcUiState.Loading

            try {
                val result = withContext(Dispatchers.IO) {
                    handleNfcTagUseCase.execute(uid)
                }

                withContext(Dispatchers.IO) {
                    registerNfcReadUseCase.execute(uid)
                }

                when (result) {
                    is NfcTagResult.NewTag ->
                        _uiState.value = NfcUiState.NewTag(uid)

                    is NfcTagResult.ExistingTag ->
                        _uiState.value = NfcUiState.KnownTag(result.alias)
                }

            } catch (e: Exception) {
                _uiState.value = NfcUiState.Error(
                    e.message ?: "Erro ao processar tag"
                )
            }
        }
    }

    fun resetState() {
        _uiState.value = NfcUiState.Idle
    }
}