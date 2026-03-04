package com.tapflow.repository

import com.tapflow.local.dao.NfcReadHistoryDao
import com.tapflow.mapper.toDomain
import com.tapflow.mapper.toEntity
import com.tapflow.model.NfcReadHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NfcReadHistoryRepositoryImpl(
    private val dao: NfcReadHistoryDao
) : NfcReadHistoryRepository {

    override suspend fun save(history: NfcReadHistory) {
        dao.insert(history.toEntity())
    }

    override fun observeAll(): Flow<List<NfcReadHistory>> {
        return dao.observeAll()
            .map { list -> list.map { it.toDomain() } }
    }
}