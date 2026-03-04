package com.tapflow.repository

import com.tapflow.local.dao.TagActionDao
import com.tapflow.mapper.toDomain
import com.tapflow.mapper.toEntity
import com.tapflow.model.TagAction

class TagActionRepositoryImpl(
    private val dao: TagActionDao
) : TagActionRepository {

    override suspend fun getAction(uid: String): TagAction? {
        return dao.getByUid(uid)?.toDomain()
    }

    override suspend fun saveAction(action: TagAction) {
        dao.insert(action.toEntity())
    }
}