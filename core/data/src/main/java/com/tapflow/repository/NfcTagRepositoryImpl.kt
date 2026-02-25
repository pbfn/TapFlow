package com.tapflow.repository

import com.tapflow.local.dao.NfcTagDao
import com.tapflow.mapper.toDomain
import com.tapflow.mapper.toEntity
import com.tapflow.model.NfcTag

class NfcTagRepositoryImpl(
    private val dao: NfcTagDao
) : NfcTagRepository {

    override fun getByUid(uid: String): NfcTag? {
        return dao.getByUid(uid)?.toDomain()
    }

    override fun save(tag: NfcTag) {
        dao.insert(tag.toEntity())
    }

}