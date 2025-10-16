package com.example.testpartnerkin.data.dto

import com.example.testpartnerkin.domain.models.DetailsData
import kotlinx.serialization.Serializable

@Serializable
data class DetailDto(
    val data: DetailsData,
    val error: String?
)