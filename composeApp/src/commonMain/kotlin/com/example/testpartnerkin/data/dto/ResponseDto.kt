package com.example.testpartnerkin.data.dto

import com.example.testpartnerkin.domain.models.Data
import kotlinx.serialization.Serializable

@Serializable
data class ResponseDto(
    val data: Data,
    val error: String?
)