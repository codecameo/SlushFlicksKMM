package com.sifat.slushflicks.di.modules

import com.sifat.slushflicks.data.remote.APPLICATION_JSON
import com.sifat.slushflicks.data.remote.ApiErrorParser
import com.sifat.slushflicks.data.remote.ApiErrorParserImpl
import com.sifat.slushflicks.data.remote.BASE_URL
import com.sifat.slushflicks.data.remote.CONTENT_TYPE
import com.sifat.slushflicks.data.remote.IMAGE_BASE_URL
import com.sifat.slushflicks.data.remote.TIMEOUT
import com.sifat.slushflicks.di.DiConstants.IMAGE_BASE_URL_NAME
import com.sifat.slushflicks.di.DiConstants.LOG_CONFIG_NAME
import io.ktor.client.HttpClient
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.addDefaultResponseValidation
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun networkModule(enableNetworkLogs: Boolean) = module {

    single(named(name = LOG_CONFIG_NAME)) { enableNetworkLogs }
    single {
        URLBuilder(
            protocol = URLProtocol.HTTPS,
            host = BASE_URL
        )
    }
    single(named(name = IMAGE_BASE_URL_NAME)) { IMAGE_BASE_URL }

    single {
        Json {
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    single<ApiErrorParser> { ApiErrorParserImpl(get()) }

    single {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer(get())
            }
            install(DefaultRequest) {
                headers.append(CONTENT_TYPE, APPLICATION_JSON)
            }
            install(HttpTimeout) {
                connectTimeoutMillis = TIMEOUT
                requestTimeoutMillis = TIMEOUT
                socketTimeoutMillis = TIMEOUT
            }
            addDefaultResponseValidation()
            if (get(named(name = LOG_CONFIG_NAME))) {
                install(Logging) {
                    logger = Logger.DEFAULT
                    level = LogLevel.BODY
                }
            }
        }
    }
}
