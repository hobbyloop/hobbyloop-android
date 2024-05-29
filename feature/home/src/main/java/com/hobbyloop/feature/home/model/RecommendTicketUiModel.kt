package com.hobbyloop.feature.home.model

import com.hobbyloop.domain.entity.ticket.RecommendTicket

data class RecommendTicketUiModel(
    val isBookMarked: Boolean,
    val imageUrl: String,
    val location: String,
    val centerName: String,
    val category: TicketCategory,
    val price: String,
    val rating: String,
    val reviewCount: String,
    val hashTags: List<TicketHashTag> = listOf()
)

fun RecommendTicket.toUiModel(): RecommendTicketUiModel {
    return RecommendTicketUiModel(
        isBookMarked = center.isBookmarked,
        imageUrl = center.imageUrl,
        location = center.address,
        centerName = center.name,
        category =  TicketCategory.PT,
        price = "${ticket.price}원 ~",
        rating = ticket.score.toString(),
        reviewCount = "(${ticket.reviewCount})"
    )
}