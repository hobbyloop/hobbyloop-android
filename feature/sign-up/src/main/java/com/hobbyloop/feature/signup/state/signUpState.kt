package com.hobbyloop.feature.signup.state

import com.hobbyloop.feature.signup.Gender


// 서버로 보낼 정보들
data class UserInfo(
    var name: String = "",
    var nickname: String = "",
    var phoneNumber: String = "",
    var birthDay: String = "",
    var gender: Gender? = null,
    // 수신 정보 동의
    var marketingConsent: Boolean = false,
    // 정보 수집 동의
    var dataCollectionConsent: Boolean = false
)


data class ValidationState(
    val isNameValid: Boolean = false,
    val isNicknameValid: Boolean = false,
    val isPhoneNumberValid: Boolean = false,
    val isBirthDayValid: Boolean = false,
    val isGenderSelected: Boolean = false,
)

data class CodeInfo(
    var code: String = "",

    var isCodeSent: Boolean = false,
    var isResendAvailable: Boolean = false,
    var isVerificationCodeValid: Boolean = false,
    var showProgress: Boolean = false,
)