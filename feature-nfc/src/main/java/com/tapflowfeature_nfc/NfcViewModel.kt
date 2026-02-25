package com.tapflowfeature_nfc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapflow.usecase.HandleNfcTagUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NfcViewModel(
    private val handleNfcTagUseCase: HandleNfcTagUseCase
) : ViewModel() {

    fun onNfcTag(uid: String) {
        viewModelScope.launch(Dispatchers.IO) {
            handleNfcTagUseCase.execute(uid)
        }
    }
}