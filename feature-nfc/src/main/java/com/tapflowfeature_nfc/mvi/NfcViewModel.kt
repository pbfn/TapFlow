package com.tapflowfeature_nfc.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapflow.NfcTagResult
import com.tapflow.usecase.HandleNfcTagUseCase
import com.tapflow.usecase.ObserveNfcHistoryUseCase
import com.tapflow.usecase.ProcessNfcTagUseCase
import com.tapflow.usecase.RegisterNfcReadUseCase
import com.tapflowfeature_nfc.NfcStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NfcViewModel(
    private val processNfcTagUseCase: ProcessNfcTagUseCase,
    private val observeNfcHistoryUseCase: ObserveNfcHistoryUseCase
) : ViewModel() {


    private val _events = MutableSharedFlow<NfcEvent>(
        extraBufferCapacity = 1
    )
    val events = _events.asSharedFlow()

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
        }
            .distinctUntilChanged()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = NfcScreenState()
            )


    private fun sendEvent(event: NfcEvent) {
        _events.tryEmit(event)
    }

    private fun reduce(status: NfcStatus) {
        _status.value = status
    }

    fun handleIntent(intent: NfcIntent) {

        when (intent) {

            is NfcIntent.TagScanned ->
                processTag(intent.uid)

            NfcIntent.ResetStatus ->
                reduce(NfcStatus.Idle)
        }
    }

    private fun processTag(uid: String) {

        viewModelScope.launch {

            reduce(NfcStatus.Loading)

            try {
                val result = withContext(Dispatchers.IO) {
                    processNfcTagUseCase.execute(uid)
                }
                val status = when (result) {
                    is NfcTagResult.NewTag ->
                        NfcStatus.NewTag(uid)

                    is NfcTagResult.ExistingTag ->
                        NfcStatus.KnownTag(result.alias)
                }
                reduce(status)
                sendEvent(NfcEvent.ShowToast("Tag lida!"))

                if (result is NfcTagResult.NewTag) {
                    sendEvent(NfcEvent.NavigateToTagConfig(uid))
                }

            } catch (e: Exception) {
                reduce(
                    NfcStatus.Error(
                        e.message ?: "Erro ao processar tag"
                    )
                )
            }
        }
    }
}