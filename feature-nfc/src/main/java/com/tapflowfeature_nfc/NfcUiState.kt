package com.tapflowfeature_nfc

sealed class NfcUiState {
    object Idle : NfcUiState()
    object Loading : NfcUiState()

    data class NewTag(val uid: String) : NfcUiState()
    data class KnownTag(val alias: String) : NfcUiState()

    data class Error(val message: String) : NfcUiState()
}