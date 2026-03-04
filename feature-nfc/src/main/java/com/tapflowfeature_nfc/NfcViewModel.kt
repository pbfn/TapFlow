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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NfcViewModel(
    private val handleNfcTagUseCase: HandleNfcTagUseCase,
    private val registerNfcReadUseCase: RegisterNfcReadUseCase,
    private val observeNfcHistoryUseCase: ObserveNfcHistoryUseCase
) : ViewModel() {

    private val _status = MutableStateFlow<NfcStatus>(NfcStatus.Idle)

    val screenState: StateFlow<NfcScreenState> =
        combine(
            observeNfcHistoryUseCase.execute(),
            _status
        ) { history, status ->
            NfcScreenState(
                history = history,
                status = status
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = NfcScreenState()
        )

    fun onNfcTag(uid: String) {
        viewModelScope.launch {
            _status.value = NfcStatus.Loading

            try {
                val result = withContext(Dispatchers.IO) {
                    handleNfcTagUseCase.execute(uid)
                }

                withContext(Dispatchers.IO) {
                    registerNfcReadUseCase.execute(uid)
                }

                _status.value = when (result) {
                    is NfcTagResult.NewTag ->
                        NfcStatus.NewTag(uid)

                    is NfcTagResult.ExistingTag ->
                        NfcStatus.KnownTag(result.alias)
                }

            } catch (e: Exception) {
                _status.value = NfcStatus.Error(
                    e.message ?: "Erro ao processar tag"
                )
            }
        }
    }
}