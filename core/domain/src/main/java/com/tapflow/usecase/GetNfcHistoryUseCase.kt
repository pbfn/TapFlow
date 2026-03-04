package com.tapflow.usecase

import com.tapflow.model.NfcReadHistory
import com.tapflow.repository.NfcReadHistoryRepository

class GetNfcHistoryUseCase(
    private val repository: NfcReadHistoryRepository
) {
    suspend fun execute(): List<NfcReadHistory> {
        return repository.getAll()
    }
}