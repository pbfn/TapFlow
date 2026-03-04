package com.tapflow.usecase

import com.tapflow.model.NfcReadHistory
import com.tapflow.repository.NfcReadHistoryRepository
import kotlinx.coroutines.flow.Flow

class ObserveNfcHistoryUseCase(
    private val repository: NfcReadHistoryRepository
) {
    fun execute(): Flow<List<NfcReadHistory>> {
        return repository.observeAll()
    }
}