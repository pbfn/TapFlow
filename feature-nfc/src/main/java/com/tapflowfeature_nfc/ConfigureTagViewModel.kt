package com.tapflowfeature_nfc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapflow.model.TagAction
import com.tapflow.model.TagActionType
import com.tapflow.usecase.SaveTagActionUseCase
import kotlinx.coroutines.launch

class ConfigureTagViewModel(
    private val saveTagActionUseCase: SaveTagActionUseCase
) : ViewModel() {

    fun saveOpenUrl(uid: String, url: String) {

        viewModelScope.launch {

            saveTagActionUseCase.execute(
                TagAction(
                    uid = uid,
                    type = TagActionType.OPEN_URL,
                    payload = url
                )
            )
        }
    }

    fun saveOpenApp(uid: String, packageName: String) {

        viewModelScope.launch {

            saveTagActionUseCase.execute(
                TagAction(
                    uid = uid,
                    type = TagActionType.OPEN_APP,
                    payload = packageName
                )
            )
        }
    }

    fun saveWebhook(uid: String, url: String) {

        viewModelScope.launch {

            saveTagActionUseCase.execute(
                TagAction(
                    uid = uid,
                    type = TagActionType.WEBHOOK,
                    payload = url
                )
            )
        }
    }
}