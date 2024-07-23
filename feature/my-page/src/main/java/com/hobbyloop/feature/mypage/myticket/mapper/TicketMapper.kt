package com.hobbyloop.feature.mypage.myticket.mapper

import com.hobbyloop.domain.entity.ticket.MonthHistory
import com.hobbyloop.domain.entity.ticket.Ticket
import com.hobbyloop.domain.entity.ticket.TicketHistory
import com.hobbyloop.domain.entity.ticket.UsageHistory
import com.hobbyloop.feature.mypage.myticket.MonthHistoryUiModel
import com.hobbyloop.feature.mypage.myticket.TicketHistoryUiModel
import com.hobbyloop.feature.mypage.myticket.UsageHistoryUiModel

fun TicketHistory.toUiModel(): TicketHistoryUiModel {
    return TicketHistoryUiModel(
        ticketId = this.id,
        ticketImageUrl = this.ticketImageUrl,
        ticketName = this.ticketName,
        centerName = this.centerName,
        remainingCount = this.remainingCount,
        totalCounting = this.totalCounting,
        usingHistoryByMonth = this.usingHistoryByMonth.map { it.toUiModel() }
    )
}

fun MonthHistory.toUiModel(): MonthHistoryUiModel {
    return MonthHistoryUiModel(
        yearMonth = this.yearMonth,
        usingHistories = this.usingHistories.map { it.toUiModel() }
    )
}

fun UsageHistory.toUiModel(): UsageHistoryUiModel {
    return UsageHistoryUiModel(
        useCount = this.useCount,
        remainingCount = this.remainingCount,
        usedAt = this.usedAt
    )
}
