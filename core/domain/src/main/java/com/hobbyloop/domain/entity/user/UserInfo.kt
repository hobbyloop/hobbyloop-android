package com.hobbyloop.domain.entity.user

data class UserInfo(
    val name: String,
    val nickname: String,
    val phoneNumber: String,
    val profileImageUrl: String,
    val points: Int,
    val ticketCount: Int,
    val couponCount: Int
)