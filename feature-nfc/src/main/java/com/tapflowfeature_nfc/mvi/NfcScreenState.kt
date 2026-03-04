package com.tapflowfeature_nfc.mvi

import com.tapflow.model.NfcReadHistory
import com.tapflowfeature_nfc.NfcStatus

data class NfcScreenState(
    val history: List<NfcReadHistory> = emptyList(),
    val status: NfcStatus = NfcStatus.Idle
)