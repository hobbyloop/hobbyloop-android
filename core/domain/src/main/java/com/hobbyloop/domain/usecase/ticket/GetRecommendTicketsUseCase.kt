package com.hobbyloop.domain.usecase.ticket

import com.hobbyloop.domain.common.Resource
import com.hobbyloop.domain.entity.ticket.HotTicket
import com.hobbyloop.domain.entity.ticket.RecommendTicket
import com.hobbyloop.domain.repository.ticket.TicketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecommendTicketsUseCase @Inject constructor(
    private val repository: TicketRepository
){
    operator fun invoke(): Flow<Resource<List<RecommendTicket>>> {
        return repository.getRecommendTickets()
    }
}