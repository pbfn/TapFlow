package com.example.tapflow

import android.app.Application
import com.example.tapflow.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class TapFlowApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TapFlowApplication)
            modules(appModules)
        }
    }
}