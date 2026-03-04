package com.tapflowfeature_nfc.executor

import com.tapflow.model.TagAction
import com.tapflow.model.TagActionType

class TagActionExecutor {

    fun execute(action: TagAction) {

        when (action.type) {

            TagActionType.OPEN_URL -> {
                // abrir navegador
            }

            TagActionType.OPEN_APP -> {
                // abrir aplicativo
            }

            TagActionType.WEBHOOK -> {
                // chamar API
            }
        }
    }
}