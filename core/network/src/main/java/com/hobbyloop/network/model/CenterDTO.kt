package com.hobbyloop.network.model

import com.hobbyloop.domain.model.Center

data class CenterDTO(
    val centerId: Long,
    val centerName: String,
    val address: String,
    val logoImageUrl: String,
    val price: Long,
    val score: Double,
    val reviewCount: Int,
    val refundable: Boolean,
    val isBookMarked: Boolean,
    val isRefundable: Boolean,
) {
    fun asUiModel(): Center {
        return Center(
            centerId = centerId,
            centerName = centerName,
            address = address,
            logoImageUrl = logoImageUrl,
            price = price,
            score = score,
            reviewCount = reviewCount,
            refundable = refundable,
            isBookMarked = isBookMarked,
            isRefundable = isRefundable
        )
    }
}
