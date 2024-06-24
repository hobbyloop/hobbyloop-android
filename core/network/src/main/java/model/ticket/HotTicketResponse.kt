package model.ticket

import com.hobbyloop.domain.entity.center.Center
import com.hobbyloop.domain.entity.ticket.HotTicket
import com.hobbyloop.domain.entity.ticket.Ticket
import model.center.CenterResponse
import model.center.toEntity

data class HotTicketResponse(
    val centerResponse: CenterResponse,
    val ticketResponse: TicketResponse
)

fun HotTicketResponse.toEntity(): HotTicket {
    return HotTicket(
        center = centerResponse.toEntity(),
        ticket = ticketResponse.toEntity()
    )
}
