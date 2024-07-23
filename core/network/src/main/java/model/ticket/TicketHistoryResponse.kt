package model.ticket

import com.hobbyloop.domain.entity.ticket.MonthHistory
import com.hobbyloop.domain.entity.ticket.Ticket
import com.hobbyloop.domain.entity.ticket.TicketHistory
import com.hobbyloop.domain.entity.ticket.UsageHistory

data class TicketHistoryResponse(
    val ticketId: Int,
    val ticketImageUrl: String,
    val ticketName: String,
    val centerName: String,
    val remainingCount: Int,
    val totalCounting: Int,
    val usingHistoryByMonth: List<MonthHistoryResponse>
)

data class MonthHistoryResponse(
    val yearMonth: String,
    val usingHistories: List<UsageHistoryResponse>
)

data class UsageHistoryResponse(
    val useCount: Int,
    val remainingCount: Int,
    val usedAt: String
)

fun TicketHistoryResponse.toDomain(): TicketHistory {
    return TicketHistory(
        id = this.ticketId,
        ticketImageUrl = this.ticketImageUrl,
        ticketName = this.ticketName,
        centerName = this.centerName,
        remainingCount = this.remainingCount,
        totalCounting = this.totalCounting,
        usingHistoryByMonth = this.usingHistoryByMonth.map { it.toDomain() }
    )
}

fun MonthHistoryResponse.toDomain(): MonthHistory {
    return MonthHistory(
        yearMonth = this.yearMonth,
        usingHistories = this.usingHistories.map { it.toDomain() }
    )
}

fun UsageHistoryResponse.toDomain(): UsageHistory {
    return UsageHistory(
        useCount = this.useCount,
        remainingCount = this.remainingCount,
        usedAt = this.usedAt
    )
}
