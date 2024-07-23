package com.hobbyloop.domain.repository.user

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import com.hobbyloop.domain.entity.user.UserInfo

interface UserInfoRepository {
    suspend fun getUserInfo(): CustomResult<UserInfo, DataError.Network>
}