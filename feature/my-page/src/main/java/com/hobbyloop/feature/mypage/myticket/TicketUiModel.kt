package com.hobbyloop.feature.mypage.myticket

data class TicketHistoryUiModel(
    val ticketId: Int,
    val ticketImageUrl: String,
    val ticketName: String,
    val centerName: String,
    val remainingCount: Int,
    val totalCounting: Int,
    val usingHistoryByMonth: List<MonthHistoryUiModel>
)

data class MonthHistoryUiModel(
    val yearMonth: String,
    val usingHistories: List<UsageHistoryUiModel>
)

data class UsageHistoryUiModel(
    val useCount: Int,
    val remainingCount: Int,
    val usedAt: String
)
