package com.hobbyloop.domain.entity.center

data class CenterInfo(
    val centerId: String,
    val centerProfileImageUrl: String,
    val centerName: String,
    val isRefundable: Boolean,
)
