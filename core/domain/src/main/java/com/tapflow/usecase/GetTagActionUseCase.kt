package com.tapflow.usecase

import com.tapflow.model.TagAction
import com.tapflow.repository.TagActionRepository

class GetTagActionUseCase(
    private val repository: TagActionRepository
) {

    suspend fun execute(uid: String): TagAction? {
        return repository.getAction(uid)
    }
}