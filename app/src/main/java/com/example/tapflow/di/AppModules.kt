package com.example.tapflow.di

import androidx.room.Room
import com.tapflow.local.database.TapFlowDatabase
import com.tapflow.repository.NfcReadHistoryRepository
import com.tapflow.repository.NfcReadHistoryRepositoryImpl
import com.tapflow.repository.NfcTagRepository
import com.tapflow.repository.NfcTagRepositoryImpl
import com.tapflow.usecase.ObserveNfcHistoryUseCase
import com.tapflow.usecase.HandleNfcTagUseCase
import com.tapflow.usecase.RegisterNfcReadUseCase
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
        )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    // DAO
    single { get<TapFlowDatabase>().nfcTagDao() }
    single { get<TapFlowDatabase>().nfcReadHistoryDao() }

    // Repository
    single<NfcTagRepository> {
        NfcTagRepositoryImpl(get())
    }

    single<NfcReadHistoryRepository> {
        NfcReadHistoryRepositoryImpl(get())
    }

    // UseCase
    factory {
        HandleNfcTagUseCase(get())
    }
    factory { RegisterNfcReadUseCase(get()) }
    factory { ObserveNfcHistoryUseCase(get()) }

    // ViewModel
    viewModel {
        NfcViewModel(get(), get(), get())
    }
}