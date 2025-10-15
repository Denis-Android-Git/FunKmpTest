package com.example.testpartnerkin.di

import com.example.testpartnerkin.data.MyLoggerImpl
import com.example.testpartnerkin.data.network.HttpClientFactory
import com.example.testpartnerkin.domain.`interface`.MyLogger
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

actual val platformModule: org.koin.core.module.Module = module {
    single<MyLogger> { MyLoggerImpl }

    single {
        HttpClientFactory(get()).createHttpClient(OkHttp.create())
    }
}