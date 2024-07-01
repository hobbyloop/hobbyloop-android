package com.hobbyloop.domain.repository.ticket

import com.hobbyloop.domain.common.Resource
import com.hobbyloop.domain.entity.ticket.HotTicket
import com.hobbyloop.domain.entity.ticket.RecommendTicket
import kotlinx.coroutines.flow.Flow

interface TicketRepository {
    fun getHotTickets(): Flow<Resource<List<HotTicket>>>
    fun getRecommendTickets(): Flow<Resource<List<RecommendTicket>>>
}