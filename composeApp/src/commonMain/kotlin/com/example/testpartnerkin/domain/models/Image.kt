package com.example.testpartnerkin.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val height: Int,
    val id: String,
    @SerialName("placeholder_color")
    val placeholderColor: String?,
    val preview: String,
    val url: String,
    val width: Int
)