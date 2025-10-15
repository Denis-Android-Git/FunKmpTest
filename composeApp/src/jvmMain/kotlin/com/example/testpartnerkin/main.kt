package com.example.testpartnerkin

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.testpartnerkin.di.initKoin

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "TestPartnerkin",
    ) {
        App()
    }
}