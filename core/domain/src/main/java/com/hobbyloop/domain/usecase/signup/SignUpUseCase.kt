package com.hobbyloop.domain.usecase.signup

import com.hobbyloop.domain.entity.signup.SignUpInfo
import com.hobbyloop.domain.entity.signup.UserSignUpResult
import com.hobbyloop.domain.repository.signup.SignUpRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: SignUpRepository
) {
    suspend operator fun invoke(signUpInfo: SignUpInfo): UserSignUpResult {
        return repository.signup(signUpInfo)
    }
}
