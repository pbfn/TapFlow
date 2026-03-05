package com.tapflowfeature_nfc.mvi

sealed interface NfcEvent {
    data class ShowToast(val message: String) : NfcEvent
    data class NavigateToTagConfig(
        val uid: String
    ) : NfcEvent
}