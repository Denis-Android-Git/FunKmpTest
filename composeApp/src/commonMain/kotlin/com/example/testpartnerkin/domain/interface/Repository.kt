package com.example.testpartnerkin.domain.`interface`

import com.example.testpartnerkin.domain.models.Result

interface Repository {
    suspend fun getResponseDto(): List<Result>
}