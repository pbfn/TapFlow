package com.tapflowfeature_nfc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapflow.usecase.HandleNfcTagUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NfcViewModel(
    private val handleNfcTagUseCase: HandleNfcTagUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<NfcUiState>(NfcUiState.Idle)
    val uiState: StateFlow<NfcUiState> = _uiState

    fun onNfcTag(uid: String) {
        viewModelScope.launch {
            _uiState.value = NfcUiState.Loading

            try {
                withContext(Dispatchers.IO){
                    handleNfcTagUseCase.execute(uid)
                    delay(2000L)
                }
                _uiState.value = NfcUiState.Success(uid)
            } catch (e: Exception) {
                _uiState.value = NfcUiState.Error(
                    message = e.message ?: "Erro ao processar tag"
                )
            }
        }
    }

    fun resetState() {
        _uiState.value = NfcUiState.Idle
    }
}