package com.tapflowfeature_nfc

sealed class NfcUiState {
    object Idle : NfcUiState()
    object Loading : NfcUiState()
    data class Success(val uid: String) : NfcUiState()
    data class Error(val message: String) : NfcUiState()
}