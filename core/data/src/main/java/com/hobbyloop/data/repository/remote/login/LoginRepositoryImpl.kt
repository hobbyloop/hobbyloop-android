package com.hobbyloop.data.repository.remote.login

import com.hobbyloop.domain.entity.login.LoginInfo
import com.hobbyloop.domain.entity.login.UserLoginResult
import com.hobbyloop.domain.repository.login.LoginRepository
import datasource.login.LoginDataSource
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource
) : LoginRepository {

    override suspend fun login(loginInfo: LoginInfo): UserLoginResult {
        return loginDataSource.login(loginInfo).data.toDomainModel()
    }

}