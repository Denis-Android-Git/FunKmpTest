package com.example.testpartnerkin.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Type(
    val id: Int,
    val name: String
)