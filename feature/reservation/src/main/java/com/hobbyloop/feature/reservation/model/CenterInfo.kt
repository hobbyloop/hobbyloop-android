package com.hobbyloop.feature.reservation.model

data class CenterInfo(
    val centerId: String,
    val centerProfileImageUrl: String,
    val centerName: String,
    val isRefundable: Boolean,
)
