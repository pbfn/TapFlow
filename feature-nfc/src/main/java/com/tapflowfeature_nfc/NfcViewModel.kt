package com.tapflowfeature_nfc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapflow.NfcTagResult
import com.tapflow.model.NfcReadHistory
import com.tapflow.usecase.GetNfcHistoryUseCase
import com.tapflow.usecase.HandleNfcTagUseCase
import com.tapflow.usecase.RegisterNfcReadUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NfcViewModel(
    private val handleNfcTagUseCase: HandleNfcTagUseCase,
    private val registerNfcReadUseCase: RegisterNfcReadUseCase,
    private val getNfcHistoryUseCase: GetNfcHistoryUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<NfcUiState>(NfcUiState.Idle)
    val uiState: StateFlow<NfcUiState> = _uiState

    private val _history =
        MutableStateFlow<List<NfcReadHistory>>(emptyList())
    val history: StateFlow<List<NfcReadHistory>> = _history

    init {
        viewModelScope.launch {
            _history.value = getNfcHistoryUseCase.execute()
        }
    }

    fun onNfcTag(uid: String) {
        viewModelScope.launch {
            _uiState.value = NfcUiState.Loading

            try {
                val result = withContext(Dispatchers.IO) {
                    handleNfcTagUseCase.execute(uid)
                }

                withContext(Dispatchers.IO) {
                    registerNfcReadUseCase.execute(uid)
                    _history.value = getNfcHistoryUseCase.execute()
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