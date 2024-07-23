package datasource.point

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.point.ExtinctionPointResponse
import model.point.HistoryResponse
import model.point.PointResponse
import javax.inject.Inject

class MockPointDataSourceImpl @Inject constructor() : PointDataSource {

    private val pointResponse = PointResponse(
        point = 50000,
        extinctionPointResponse = ExtinctionPointResponse(
            point = 1000,
            date = "2023-06-06"
        ),
        history = listOf(
            HistoryResponse(
                point = 1000,
                type = 0,
                date = "2023-06-06 11:11:11.111",
                totalPoint = 3000
            ),
            HistoryResponse(
                point = 1000,
                type = 0,
                date = "2023-06-06 11:11:11.111",
                totalPoint = 3000
            ),
            HistoryResponse(
                point = 1000,
                type = 1,
                date = "2023-06-06 11:11:11.111",
                totalPoint = 3000
            ),
            HistoryResponse(
                point = 1000,
                type = 0,
                date = "2023-06-06 11:11:11.111",
                totalPoint = 3000
            ),
            HistoryResponse(
                point = 1000,
                type = 1,
                date = "2023-06-06 11:11:11.111",
                totalPoint = 3000
            )
        )
    )

    override fun getPointHistory(): Flow<CustomResult<PointResponse, DataError.Network>> = flow {
        delay(1000)
        val result: CustomResult<PointResponse, DataError.Network> = CustomResult.Success(pointResponse)
        emit(result)
    }
}