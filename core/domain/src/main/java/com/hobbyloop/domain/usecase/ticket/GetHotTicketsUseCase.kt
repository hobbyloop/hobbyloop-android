package com.hobbyloop.domain.usecase.ticket

import com.hobbyloop.domain.common.Resource
import com.hobbyloop.domain.entity.ad.Ad
import com.hobbyloop.domain.entity.ticket.HotTicket
import com.hobbyloop.domain.repository.ad.AdRepository
import com.hobbyloop.domain.repository.ticket.TicketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHotTicketsUseCase @Inject constructor(
    private val repository: TicketRepository
) {
    operator fun invoke(): Flow<Resource<List<HotTicket>>> {
        return repository.getHotTickets()
    }
}