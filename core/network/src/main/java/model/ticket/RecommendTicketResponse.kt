package model.ticket

import com.hobbyloop.domain.entity.ticket.RecommendTicket
import model.center.CenterResponse
import model.center.toEntity


data class RecommendTicketResponse(
    val centerResponse: CenterResponse,
    val ticketResponse: TicketResponse
)

fun RecommendTicketResponse.toEntity(): RecommendTicket {
    return RecommendTicket(
        center = centerResponse.toEntity(),
        ticket = ticketResponse.toEntity()
    )
}