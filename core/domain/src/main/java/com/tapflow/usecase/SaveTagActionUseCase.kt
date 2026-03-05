package com.tapflow.usecase

import com.tapflow.model.TagAction
import com.tapflow.repository.TagActionRepository

class SaveTagActionUseCase(
    private val repository: TagActionRepository
) {

    suspend fun execute(action: TagAction) {
        repository.saveAction(action)
    }
}