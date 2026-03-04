package com.tapflowfeature_nfc

sealed interface NfcStatus {
    object Idle : NfcStatus
    object Loading : NfcStatus
    data class NewTag(val uid: String) : NfcStatus
    data class KnownTag(val alias: String) : NfcStatus
    data class Error(val message: String) : NfcStatus
}