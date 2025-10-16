package com.example.testpartnerkin.data

import com.example.testpartnerkin.data.dto.ResponseDto
import com.example.testpartnerkin.core.UrlConstant
import com.example.testpartnerkin.data.dto.DetailDto
import com.example.testpartnerkin.domain.`interface`.Repository
import com.example.testpartnerkin.domain.models.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url

class RepositoryImpl(
    private val httpClient: HttpClient,
) : Repository {
    override suspend fun getResponseDto(): List<Result> {
        val responseDto: ResponseDto = httpClient.get {
            url(UrlConstant.BASE_URL)
        }.body()

        return responseDto.data.result
    }

    override suspend fun getDetailsDto(): DetailDto {
        return httpClient.get {
            url(UrlConstant.DETAIL_URL)
        }.body()
    }
}