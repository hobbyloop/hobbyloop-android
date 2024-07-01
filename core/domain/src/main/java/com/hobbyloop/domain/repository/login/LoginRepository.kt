package com.hobbyloop.domain.repository.login

import com.hobbyloop.domain.entity.login.LoginInfo
import com.hobbyloop.domain.entity.login.UserLoginResult

interface LoginRepository {
    suspend fun login(loginInfo: LoginInfo): UserLoginResult

}

