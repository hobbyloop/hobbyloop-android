package com.hobbyloop.feature.mypage

import com.hobbyloop.domain.entity.user.UserInfo

data class UserInfoUiModel(
    val name: String,
    val nickname: String,
    val phoneNumber: String,
    val profileImageUrl: String,
    val points: Int,
    val ticketCount: Int,
    val couponCount: Int
)

fun UserInfo.toUiModel() = UserInfoUiModel(
    name = name,
    nickname = nickname,
    phoneNumber = phoneNumber,
    profileImageUrl = profileImageUrl,
    points = points,
    ticketCount = ticketCount,
    couponCount = couponCount
)
