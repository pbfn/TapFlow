package com.example.tapflow

import android.app.Application

class TapFlowApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppGraph.init(this)
    }
}