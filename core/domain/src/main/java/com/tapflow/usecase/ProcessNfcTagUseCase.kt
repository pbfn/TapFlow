package com.tapflow.usecase

import com.tapflow.NfcTagResult

class ProcessNfcTagUseCase(
    private val handleNfcTagUseCase: HandleNfcTagUseCase,
    private val registerNfcReadUseCase: RegisterNfcReadUseCase
) {

    suspend fun execute(uid: String): NfcTagResult {

        val result = handleNfcTagUseCase.execute(uid)

        registerNfcReadUseCase.execute(uid)

        return result
    }
}