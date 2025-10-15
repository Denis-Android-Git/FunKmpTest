package com.example.testpartnerkin

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.testpartnerkin.presentation.ui.MainScreenState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        MainScreenState()
    }
}