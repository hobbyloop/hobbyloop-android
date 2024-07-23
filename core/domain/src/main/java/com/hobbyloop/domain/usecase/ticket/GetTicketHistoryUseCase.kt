package com.hobbyloop.domain.usecase.ticket

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import com.hobbyloop.domain.entity.ticket.TicketHistory
import com.hobbyloop.domain.repository.ticket.TicketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTicketHistoryUseCase @Inject constructor(
    private val ticketRepository: TicketRepository
) {
    operator fun invoke(): Flow<CustomResult<List<TicketHistory>, DataError.Network>> {
        return ticketRepository.getTicketHistory()
    }
}