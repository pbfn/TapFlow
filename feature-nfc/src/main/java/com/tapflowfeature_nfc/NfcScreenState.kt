package com.tapflowfeature_nfc

import com.tapflow.model.NfcReadHistory

data class NfcScreenState(
    val history: List<NfcReadHistory> = emptyList(),
    val status: NfcStatus = NfcStatus.Idle
)