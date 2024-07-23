package datasource.point

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import datasource.BaseDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.delay
import model.common.ApiResponse
import model.point.PointTotalHistoryResponse
import model.user.UserInfoResponse
import javax.inject.Inject

class PointDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : BaseDataSource(), PointDataSource {

    override suspend fun getPointTotalHistory(): CustomResult<PointTotalHistoryResponse, DataError.Network> = request {
        client.get("/api/v1/points/histories").body<ApiResponse<PointTotalHistoryResponse>>()
    }
}