package datasource.ticket

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import kotlinx.coroutines.flow.Flow
import model.ticket.HotTicketResponse
import model.ticket.RecommendTicketResponse

interface TicketDataSource {
    fun getHotTickets(): Flow<CustomResult<List<HotTicketResponse>, DataError.Network>>
    fun getRecommendTickets(): Flow<CustomResult<List<RecommendTicketResponse>, DataError.Network>>
}