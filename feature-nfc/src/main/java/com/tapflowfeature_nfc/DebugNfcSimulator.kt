package com.tapflowfeature_nfc

import com.tapflow.data.repository.FakeNfcTagRepository
import com.tapflow.usecase.HandleNfcTagUseCase

object DebugNfcSimulator {
    private val repository = FakeNfcTagRepository()
    private val useCase = HandleNfcTagUseCase(repository)

    fun simulate(uid: String) {
        useCase.execute(uid)
    }
}