package com.example.testpartnerkin.presentation.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.testpartnerkin.presentation.viewModels.MainScreenViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreenState(
    viewModel: MainScreenViewModel = koinViewModel(),
    onClick: (Int) -> Unit
) {
    val state = viewModel.mainScreenData.collectAsStateWithLifecycle()

    MainScreen(
        confList = state.value.confList,
        isLoading = state.value.isLoading,
        error = state.value.error,
        onClick = onClick
    )
}