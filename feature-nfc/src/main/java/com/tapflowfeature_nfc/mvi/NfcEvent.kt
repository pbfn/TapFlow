package com.tapflowfeature_nfc.mvi

sealed interface NfcEvent {
    data class ShowToast(val message: String) : NfcEvent
    object NavigateToConfig : NfcEvent
}