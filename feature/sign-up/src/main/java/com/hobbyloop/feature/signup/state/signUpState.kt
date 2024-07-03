package com.hobbyloop.feature.signup.state

import androidx.compose.runtime.Stable
import com.hobbyloop.feature.signup.Gender


@Stable
data class UserInfo(
    val name: String = "",
    val nickname: String = "",
    val phoneNumber: String = "",
    val birthDay: String = "",
    val gender: Gender? = null,
    val marketingConsent: Boolean = false,
    val dataCollectionConsent: Boolean = false
)

@Stable
data class ValidationState(
    val isNameValid: Boolean = false,
    val isNicknameValid: Boolean = false,
    val isPhoneNumberValid: Boolean = false,
    val isBirthDayValid: Boolean = false,
    val isGenderSelected: Boolean = false,
)

@Stable
data class CodeInfo(
    val code: String = "",
    val isCodeSent: Boolean = false,
    val isResendAvailable: Boolean = false,
    val isVerificationCodeValid: Boolean = false,
    val showProgress: Boolean = false,
)