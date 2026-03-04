package com.tapflow.usecase

import com.tapflow.model.NfcReadHistory
import com.tapflow.repository.NfcReadHistoryRepository

class RegisterNfcReadUseCase(
    private val repository: NfcReadHistoryRepository
) {
    suspend fun execute(uid: String) {
        repository.save(
            NfcReadHistory(
                uid = uid,
                timestamp = System.currentTimeMillis()
            )
        )
    }
}