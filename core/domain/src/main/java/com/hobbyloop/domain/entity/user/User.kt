package com.hobbyloop.domain.entity.user

data class User(
    val nickname: String,
    val phoneNumber: String,
    val email: String,
    val profileUrl: String,
    val point: Int,
    val couponCount: Int,
    val reviewCount: Int,
)
