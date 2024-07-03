package com.hobbyloop.domain.common

sealed class NetWorkException(message: String) : Exception(message) {
    class NotFoundException(message: String) : NetWorkException(message)
    class RedirectionException(message: String) : NetWorkException(message)
    class AuthorizationException(message: String) : NetWorkException(message)
    class ClientException(message: String) : NetWorkException(message)
    class ServerException(message: String) : NetWorkException(message)
    class UnknownException(message: String) : NetWorkException(message)
}