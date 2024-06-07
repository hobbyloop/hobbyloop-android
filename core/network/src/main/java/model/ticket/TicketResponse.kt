package model.ticket

import com.hobbyloop.domain.entity.ticket.Ticket

data class TicketResponse(
    val id: Int,
    val name: String,
    val score: Double,
    val reviewCount: Int,
    val category: String,
    val price: Int,
)

fun TicketResponse.toEntity(): Ticket {
    return Ticket(
        id = this.id,
        name = this.name,
        score = this.score,
        reviewCount = this.reviewCount,
        category = this.category,
        price = this.price
    )
}
