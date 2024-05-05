package com.hobbyloop.feature.home.model

data class HotTicketUiModel(
    val isRefundable: Boolean,
    val isBookMarked: Boolean,
    val imageUrl: String,
    val location: String,
    val centerName: String,
    val price: String,
    val rating: String,
    val countOfReview: String,
)
