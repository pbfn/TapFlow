package com.example.tapflow

object DebugNfcSimulator {
    fun simulate(uid: String) {
        AppGraph.nfcTagUseCase.execute(uid)
    }
}