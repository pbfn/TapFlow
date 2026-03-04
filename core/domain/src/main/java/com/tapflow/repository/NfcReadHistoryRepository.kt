package com.tapflow.repository

import com.tapflow.model.NfcReadHistory

interface NfcReadHistoryRepository {
    suspend fun save(history: NfcReadHistory)
    suspend fun getAll(): List<NfcReadHistory>
}