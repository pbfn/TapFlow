package com.tapflow.repository

import com.tapflow.model.NfcReadHistory
import kotlinx.coroutines.flow.Flow

interface NfcReadHistoryRepository {
    suspend fun save(history: NfcReadHistory)
    fun observeAll(): Flow<List<NfcReadHistory>>
}