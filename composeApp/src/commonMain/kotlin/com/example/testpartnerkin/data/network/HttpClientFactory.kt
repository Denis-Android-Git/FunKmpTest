package com.example.testpartnerkin.data.network

import com.example.testpartnerkin.domain.`interface`.MyLogger
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class HttpClientFactory(
    private val myLogger: MyLogger
) {
    fun createHttpClient(engine: HttpClientEngine): HttpClient {
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(HttpTimeout) {
                connectTimeoutMillis = 50000
                requestTimeoutMillis = 50000
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        myLogger.debug(message)
                    }
                }
                level = LogLevel.ALL
            }
        }
    }
}