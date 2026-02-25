package com.example.tapflow

import android.content.Context
import androidx.room.Room
import com.tapflow.local.database.TapFlowDatabase
import com.tapflow.repository.NfcTagRepositoryImpl
import com.tapflow.usecase.HandleNfcTagUseCase

object AppGraph {

    lateinit var nfcTagUseCase: HandleNfcTagUseCase
        private set

    fun init(context: Context) {
        val db = Room.databaseBuilder(
            context,
            TapFlowDatabase::class.java,
            "tapflow.db"
        )
            .allowMainThreadQueries() // TEMPORÁRIO
            .build()

        val repository = NfcTagRepositoryImpl(
            dao = db.nfcTagDao()
        )

        nfcTagUseCase = HandleNfcTagUseCase(repository)
    }
}