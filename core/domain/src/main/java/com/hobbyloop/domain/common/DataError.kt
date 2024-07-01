package com.hobbyloop.domain.common

sealed interface DataError: Error {
    enum class Network: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        CLIENT_ERROR,
        SERIALIZATION,
        UNAUTHORIZED,
        REDIRECTION,
        UNKNOWN,
    }
    enum class Local: DataError {
        DISK_FULL
    }
}