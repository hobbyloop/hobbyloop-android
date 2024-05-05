package com.hobbyloop.feature.home.model

data class RecommendTicketUiModel(
    val isBookMarked: Boolean,
    val imageUrl: String,
    val category: TicketCategory,
    val location: String,
    val centerName: String,
    val price: String,
    val rating: String,
    val countOfReview: String,
    val hashTags: List<TicketHashTag> = listOf()
)