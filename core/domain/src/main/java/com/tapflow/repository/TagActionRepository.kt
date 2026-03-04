package com.tapflow.repository

import com.tapflow.model.TagAction

interface TagActionRepository {

    suspend fun getAction(uid: String): TagAction?

    suspend fun saveAction(action: TagAction)
}