package com.example.testpartnerkin.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Data(
    val counts: Int,
    val pagination: Pagination,
    val result: List<Result>
)