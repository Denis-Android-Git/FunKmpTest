package com.example.testpartnerkin.presentation.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.testpartnerkin.presentation.viewModels.DetailScreenViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailScreenState(
    id: Int, //получаем id для боевого приложения
    detailScreenViewModel: DetailScreenViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {

    val state = detailScreenViewModel.detailScreenState.collectAsStateWithLifecycle()

    DetailScreen(
        detailsData = state.value.detailsData,
        isLoading = state.value.isLoading,
        error = state.value.error,
        onBackClick = onBackClick
    )
}