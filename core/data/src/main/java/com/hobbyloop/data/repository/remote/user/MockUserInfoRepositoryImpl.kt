package com.hobbyloop.data.repository.remote.user


import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import com.hobbyloop.domain.entity.user.UserInfo
import com.hobbyloop.domain.repository.user.UserInfoRepository
import datasource.user.UserInfoDataSource
import model.user.toDomain
import javax.inject.Inject


class MockUserInfoRepositoryImpl @Inject constructor(
    private val dataSource: UserInfoDataSource
) : UserInfoRepository {

    override suspend fun getUserInfo(): CustomResult<UserInfo, DataError.Network> {
        return when (val result = dataSource.getUserInfo()) {
            is CustomResult.Success -> CustomResult.Success(result.data.toDomain())
            is CustomResult.Error -> CustomResult.Error(result.error)
        }
    }
}