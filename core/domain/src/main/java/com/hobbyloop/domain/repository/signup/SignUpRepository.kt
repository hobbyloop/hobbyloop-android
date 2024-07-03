package com.hobbyloop.domain.repository.signup

import com.hobbyloop.domain.entity.signup.SignUpInfo
import com.hobbyloop.domain.entity.signup.UserSignUpResult

interface SignUpRepository {
    suspend fun signup(signupInfo: SignUpInfo): UserSignUpResult

}