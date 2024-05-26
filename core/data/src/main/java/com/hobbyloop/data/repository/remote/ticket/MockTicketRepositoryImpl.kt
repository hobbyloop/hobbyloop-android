package com.hobbyloop.data.repository.remote.ticket

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import com.hobbyloop.domain.common.Resource
import com.hobbyloop.domain.entity.ticket.HotTicket
import com.hobbyloop.domain.entity.ticket.RecommendTicket
import com.hobbyloop.domain.repository.ticket.TicketRepository
import datasource.ticket.TicketDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import model.ticket.toEntity
import javax.inject.Inject

class MockTicketRepositoryImpl @Inject constructor(
    private val dataSource: TicketDataSource
) : TicketRepository {
    override fun getHotTickets(): Flow<Resource<List<HotTicket>>> {
        return dataSource.getHotTickets()
            .catch { e -> emit(CustomResult.Error(DataError.Network.UNKNOWN)) }
            .map { result ->
                when (result) {
                    is CustomResult.Success -> Resource.Success(result.data.map { it.toEntity() })
                    is CustomResult.Error -> Resource.Error("result.error", null)
                }
            }
    }

    override fun getRecommendTickets(): Flow<Resource<List<RecommendTicket>>> {
        return dataSource.getRecommendTickets()
            .catch { e -> emit(CustomResult.Error(DataError.Network.UNKNOWN)) }
            .map { result ->
                when (result) {
                    is CustomResult.Success -> Resource.Success(result.data.map { it.toEntity() })
                    is CustomResult.Error -> Resource.Error("result.error", null)
                }
            }
    }
}