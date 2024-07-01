package com.hobbyloop.network.di

import android.util.Log
import com.hobbyloop.domain.common.DataError
import com.hobbyloop.domain.common.NetWorkException
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(Android) {
        install(HttpTimeout) { // Timeout configuration
            requestTimeoutMillis = 10000
            connectTimeoutMillis = 10000
            socketTimeoutMillis = 10000
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            url("https://hobbyloop.kr")
        }

        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception: Throwable, _: HttpRequest ->
                throw mapToCustomException(NetworkExceptionMapper.mapException(exception))
            }

            validateResponse { response ->
                val error = NetworkExceptionMapper.mapResponse(response)
                if (error != DataError.Network.UNKNOWN) {
                    throw mapToCustomException(error)
                }
            }
        }

        ResponseObserver { response ->
            Log.d("HTTP status: ", "${response.status.value}")
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

    private fun mapToCustomException(dataError: DataError.Network): NetWorkException {
        return when (dataError) {
            DataError.Network.UNAUTHORIZED -> NetWorkException.AuthorizationException("Authorization failed")
            DataError.Network.CLIENT_ERROR -> NetWorkException.ClientException("Client error")
            DataError.Network.SERVER_ERROR -> NetWorkException.ServerException("Server error")
            DataError.Network.REDIRECTION -> NetWorkException.RedirectionException("Redirection error")
            else -> NetWorkException.UnknownException("Unknown error")
        }
    }
}

object NetworkExceptionMapper {

    fun mapException(exception: Throwable): DataError.Network {
        return when (exception) {
            is RedirectResponseException -> DataError.Network.REDIRECTION
            is ClientRequestException -> {
                when (exception.response.status.value) {
                    401 -> DataError.Network.UNAUTHORIZED
                    else -> DataError.Network.CLIENT_ERROR
                }
            }

            is ServerResponseException -> DataError.Network.SERVER_ERROR
            else -> DataError.Network.UNKNOWN
        }
    }

    fun mapResponse(response: HttpResponse): DataError.Network {
        return when (response.status.value) {
            401 -> DataError.Network.UNAUTHORIZED
            in 400..499 -> DataError.Network.CLIENT_ERROR
            in 500..599 -> DataError.Network.SERVER_ERROR
            else -> DataError.Network.UNKNOWN
        }
    }
}



