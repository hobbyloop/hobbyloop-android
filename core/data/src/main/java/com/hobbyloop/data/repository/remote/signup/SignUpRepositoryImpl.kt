package com.hobbyloop.data.repository.remote.signup

import com.hobbyloop.domain.entity.signup.SignUpInfo
import com.hobbyloop.domain.entity.signup.UserSignUpResult
import com.hobbyloop.domain.repository.signup.SignUpRepository
import datasource.signup.SignUpDataSource
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val signUpDataSource: SignUpDataSource
) : SignUpRepository {

    override suspend fun signup(signupInfo: SignUpInfo): UserSignUpResult {
        return signUpDataSource.signup(signupInfo).data.toDomainModel()
    }


}