package com.hobbyloop.domain.usecase.login

import com.hobbyloop.domain.entity.login.LoginInfo
import com.hobbyloop.domain.entity.login.UserLoginResult
import com.hobbyloop.domain.repository.login.LoginRepository
import javax.inject.Inject

class GetJWTUseCase @Inject constructor(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(loginInfo: LoginInfo): UserLoginResult {
        return repository.login(loginInfo)
    }
}

