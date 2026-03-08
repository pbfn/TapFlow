package com.tapflow.kmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "TapFlow") {
        TapFlowKmpApp()
    }
}

actual fun platformName(): String = "Desktop"
