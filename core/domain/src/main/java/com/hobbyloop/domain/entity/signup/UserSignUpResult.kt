package com.hobbyloop.domain.entity.signup

data class UserSignUpResult(
    val accessToken: String,
    val refreshToken: String,
)