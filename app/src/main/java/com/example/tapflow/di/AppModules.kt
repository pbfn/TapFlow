package com.example.tapflow.di

import androidx.room.Room
import com.tapflow.local.database.TapFlowDatabase
import com.tapflow.repository.NfcTagRepository
import com.tapflow.repository.NfcTagRepositoryImpl
import com.tapflow.usecase.HandleNfcTagUseCase
import com.tapflowfeature_nfc.NfcViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    // Database
    single {
        Room.databaseBuilder(
            androidContext(),
            TapFlowDatabase::class.java,
            "tapflow.db"
        ).build()
    }

    // DAO
    single { get<TapFlowDatabase>().nfcTagDao() }

    // Repository
    single<NfcTagRepository> {
        NfcTagRepositoryImpl(get())
    }

    // UseCase
    factory {
        HandleNfcTagUseCase(get())
    }

    // ViewModel
    viewModel {
        NfcViewModel(get())
    }
}