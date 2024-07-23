package com.hobbyloop.domain.usecase.user

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import com.hobbyloop.domain.entity.user.UserInfo
import com.hobbyloop.domain.repository.user.UserInfoRepository
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    suspend operator fun invoke(): CustomResult<UserInfo, DataError.Network> {
        return userInfoRepository.getUserInfo()
    }
}