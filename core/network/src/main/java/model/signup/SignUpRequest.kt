package model.signup

import kotlinx.serialization.Serializable
import util.DateSerializer
import java.time.LocalDate

@Serializable
data class SignUpRequest(
    val name: String,
    val email: String,
    val nickname: String,
    val gender: Int,
    @Serializable(with = DateSerializer::class)
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

