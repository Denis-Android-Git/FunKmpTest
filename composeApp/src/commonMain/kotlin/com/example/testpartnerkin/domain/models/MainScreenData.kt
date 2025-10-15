package com.example.testpartnerkin.domain.models

data class MainScreenData(
    val isLoading: Boolean = false,
    val error: String? = null,
    val confList: List<Result> = emptyList()
)
