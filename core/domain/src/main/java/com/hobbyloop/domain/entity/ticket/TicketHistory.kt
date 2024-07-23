package com.hobbyloop.domain.entity.ticket

data class TicketHistory(
    val id: Int,
    val ticketImageUrl: String,
    val ticketName: String,
    val centerName: String,
    val remainingCount: Int,
    val totalCounting: Int,
    val usingHistoryByMonth: List<MonthHistory>
)

data class MonthHistory(
    val yearMonth: String,
    val usingHistories: List<UsageHistory>
)

data class UsageHistory(
    val useCount: Int,
    val remainingCount: Int,
    val usedAt: String
)
