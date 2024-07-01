package datasource.ticket

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.center.CenterResponse
import model.ticket.HotTicketResponse
import model.ticket.RecommendTicketResponse
import model.ticket.TicketResponse
import javax.inject.Inject

class MockTicketDataSourceImpl @Inject constructor() : TicketDataSource {
    private val hotTickets = listOf(
        HotTicketResponse(
            CenterResponse(
                1,
                "시설이름1",
                "https://hobbyloop.s3.ap-northeast-2.amazonaws.com/CenterImage/cccd10d4-bce3-4792-956d-81ebd76e8f80-%E1%84%92%E1%85%A1%E1%84%82%E1%85%B3%E1%86%AF2.jpeg",
                "address2",
                true
            ),
            TicketResponse(
                1,
                "name1",
                4.8,
                12,
                "필라테스",
                200000
            )
        ),
        HotTicketResponse(
            CenterResponse(
                2,
                "시설이름2",
                "https://hobbyloop.s3.ap-northeast-2.amazonaws.com/CenterImage/cccd10d4-bce3-4792-956d-81ebd76e8f80-%E1%84%92%E1%85%A1%E1%84%82%E1%85%B3%E1%86%AF2.jpeg",
                "address2",
                true
            ),
            TicketResponse(
                2,
                "name2",
                3.5,
                9,
                "필라테스",
                880000
            )
        ),
        HotTicketResponse(
            CenterResponse(
                3,
                "시설이름3",
                "https://hobbyloop.s3.ap-northeast-2.amazonaws.com/CenterImage/cccd10d4-bce3-4792-956d-81ebd76e8f80-%E1%84%92%E1%85%A1%E1%84%82%E1%85%B3%E1%86%AF2.jpeg",
                "address2",
                true
            ),
            TicketResponse(
                3,
                "name3",
                0.0,
                0,
                "필라테스",
                400000
            )
        ),

        )

    private val recommendTickets = listOf(
        RecommendTicketResponse(
            CenterResponse(
                1,
                "시설이름1",
                "https://hobbyloop.s3.ap-northeast-2.amazonaws.com/CenterImage/cccd10d4-bce3-4792-956d-81ebd76e8f80-%E1%84%92%E1%85%A1%E1%84%82%E1%85%B3%E1%86%AF2.jpeg",
                "address2",
                true
            ),
            TicketResponse(
                1,
                "name1",
                4.8,
                12,
                "필라테스",
                200000
            )
        ),
        RecommendTicketResponse(
            CenterResponse(
                2,
                "시설이름2",
                "https://hobbyloop.s3.ap-northeast-2.amazonaws.com/CenterImage/cccd10d4-bce3-4792-956d-81ebd76e8f80-%E1%84%92%E1%85%A1%E1%84%82%E1%85%B3%E1%86%AF2.jpeg",
                "address2",
                true
            ),
            TicketResponse(
                2,
                "name2",
                3.5,
                9,
                "필라테스",
                880000
            )
        ),
        RecommendTicketResponse(
            CenterResponse(
                3,
                "시설이름3",
                "https://hobbyloop.s3.ap-northeast-2.amazonaws.com/CenterImage/cccd10d4-bce3-4792-956d-81ebd76e8f80-%E1%84%92%E1%85%A1%E1%84%82%E1%85%B3%E1%86%AF2.jpeg",
                "address2",
                true
            ),
            TicketResponse(
                3,
                "name3",
                0.0,
                0,
                "필라테스",
                400000
            )
        ),

        )

    override fun getHotTickets(): Flow<CustomResult<List<HotTicketResponse>, DataError.Network>> {
        return flow {
            try {
                val result: CustomResult<List<HotTicketResponse>, DataError.Network> = CustomResult.Success(hotTickets)
                emit(result)
            } catch (e: Exception) {

            }
        }
    }

    override fun getRecommendTickets(): Flow<CustomResult<List<RecommendTicketResponse>, DataError.Network>> {
        return flow {
            try {
                val result: CustomResult<List<RecommendTicketResponse>, DataError.Network> = CustomResult.Success(recommendTickets)
                emit(result)
            } catch (e: Exception) {

            }
        }
    }
}