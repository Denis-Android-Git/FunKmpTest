package com.example.testpartnerkin.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val conference: Conference,
    @SerialName("view_type")
    val viewType: Int
)