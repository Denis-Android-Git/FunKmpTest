package com.example.testpartnerkin

import androidx.compose.ui.window.ComposeUIViewController
import com.example.testpartnerkin.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {

    App()
}