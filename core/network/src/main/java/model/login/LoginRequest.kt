package model.login

import kotlinx.serialization.Serializable


@Serializable
data class LoginRequest(
    val accessToken: String,
    val provider: String
)