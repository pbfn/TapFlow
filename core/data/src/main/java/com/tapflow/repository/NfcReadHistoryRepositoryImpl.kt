package com.tapflow.repository

import com.tapflow.local.dao.NfcReadHistoryDao
import com.tapflow.mapper.toDomain
import com.tapflow.mapper.toEntity
import com.tapflow.model.NfcReadHistory

class NfcReadHistoryRepositoryImpl(
    private val dao: NfcReadHistoryDao
) : NfcReadHistoryRepository {

    override suspend fun save(history: NfcReadHistory) {
        dao.insert(history.toEntity())
    }

    override suspend fun getAll(): List<NfcReadHistory> {
        return dao.getAll().map { it.toDomain() }
    }
}