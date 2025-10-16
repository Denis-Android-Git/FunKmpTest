package com.example.testpartnerkin.domain.models

data class DetailScreenData(
    val isLoading: Boolean = false,
    val error: String? = null,
    val detailsData: DetailsData? = null
)
