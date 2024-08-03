package com.hobbyloop.network.di

import android.util.Log
import com.hobbyloop.domain.repository.user.UserDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import model.common.ErrorResponse
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        userDataRepository: UserDataRepository
    ): HttpClient = HttpClient(Android) {
        install(HttpTimeout) {
            requestTimeoutMillis = 10000
            connectTimeoutMillis = 10000
            socketTimeoutMillis = 10000
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            url("https://hobbyloop.kr")
            header(HttpHeaders.Authorization, "Bearer ${runBlocking { userDataRepository.userData.first().jwt }}")
        }

        HttpResponseValidator {
            validateResponse { response ->
                if (!response.status.isSuccess()) {
                    val errorResponse = response.body<ErrorResponse>()
                    throw ResponseException(response, errorResponse.errorMessage)
                }
            }
        }

        Logging {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("HTTP Logger: ", message)
                }
            }
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            json(Json {
                encodeDefaults = false
                ignoreUnknownKeys = true
                isLenient = true
                useAlternativeNames = false
            })
        }
    }
}

