package com.hobbyloop.feature.home.model

import com.hobbyloop.domain.entity.ticket.HotTicket

data class HotTicketUiModel(
    val isRefundable: Boolean,
    val isBookMarked: Boolean,
    val imageUrl: String,
    val location: String,
    val centerName: String,
    val price: String,
    val rating: String,
    val reviewCount: String,
)

fun HotTicket.toUiModel(): HotTicketUiModel {
    return HotTicketUiModel(
        isRefundable = true,
        isBookMarked = center.isBookmarked,
        imageUrl = center.imageUrl,
        location = center.address,
        centerName = center.name,
        price = "${ticket.price}원 ~",
        rating = ticket.score.toString(),
        reviewCount = "(${ticket.reviewCount})"
    )
}