package com.tapflowfeature_nfc.mvi

sealed interface NfcIntent {

    data class TagScanned(
        val uid: String
    ) : NfcIntent

    object ResetStatus : NfcIntent
}