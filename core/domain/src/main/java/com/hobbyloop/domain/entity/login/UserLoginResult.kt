package com.hobbyloop.domain.entity.login

import kotlinx.serialization.Serializable

@Serializable
data class UserLoginResult(
    val accessToken: String?,
    val refreshToken: String?,
    val email: String?,
    val provider: String?,
    val subject: String?,
    val oauth2AccessToken: String?
)