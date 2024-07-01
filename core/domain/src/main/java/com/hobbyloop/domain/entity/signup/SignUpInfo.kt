package com.hobbyloop.domain.entity.signup

import java.time.LocalDate

data class SignUpInfo(
    val name: String,
    val email: String,
    val nickname: String,
    val gender: Int,
    val birthday: LocalDate,
    val phoneNumber: String,
    val isOption1: Boolean,
    val isOption2: Boolean,
    val provider: String,
    val subject: String,
    val oauth2AccessToken: String,
    val ci: String,
    val di: String
)