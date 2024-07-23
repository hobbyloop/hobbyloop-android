package datasource.point

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import datasource.BaseDataSource
import model.point.PointDayHistoryResponse
import model.point.PointMonthHistoryResponse
import model.point.PointTotalHistoryResponse
import javax.inject.Inject

class MockPointDataSourceImpl @Inject constructor() : BaseDataSource(), PointDataSource {

    override suspend fun getPointTotalHistory(): CustomResult<PointTotalHistoryResponse, DataError.Network> {
        val mockResponse = PointTotalHistoryResponse(
            totalPoints = 50000,
            pointHistories = listOf(
                PointMonthHistoryResponse(
                    yearMonth = "2024/06",
                    pointHistories = listOf(
                        PointDayHistoryResponse(
                            type = "EARN",
                            amount = 2500,
                            balance = 4200,
                            description = "하비루프 스튜디오 이용권 사용",
                            createdAt = "2024-07-17T12:52:56.349Z"
                        ),
                        PointDayHistoryResponse(
                            type = "EARN",
                            amount = 2500,
                            balance = 4200,
                            description = "하비루프 스튜디오 이용권 사용",
                            createdAt = "2024-07-17T12:52:56.349Z"
                        ),
                        PointDayHistoryResponse(
                            type = "EARN",
                            amount = 2500,
                            balance = 4200,
                            description = "하비루프 스튜디오 이용권 사용",
                            createdAt = "2024-07-17T12:52:56.349Z"
                        )
                    )
                ),
                PointMonthHistoryResponse(
                    yearMonth = "2024/05",
                    pointHistories = listOf(
                        PointDayHistoryResponse(
                            type = "SPEND",
                            amount = 1500,
                            balance = 2700,
                            description = "하비루프 카페 결제",
                            createdAt = "2024-05-16T10:45:30.123Z"
                        ),
                        PointDayHistoryResponse(
                            type = "EARN",
                            amount = 1000,
                            balance = 3700,
                            description = "하비루프 운동 참여",
                            createdAt = "2024-05-10T08:20:10.789Z"
                        ),
                        PointDayHistoryResponse(
                            type = "SPEND",
                            amount = 1200,
                            balance = 2500,
                            description = "하비루프 상품 구매",
                            createdAt = "2024-05-01T14:32:56.349Z"
                        )
                    )
                )
            )
        )
        return CustomResult.Success(mockResponse)
    }
}