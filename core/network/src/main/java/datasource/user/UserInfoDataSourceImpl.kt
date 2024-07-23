package datasource.user

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import datasource.BaseDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import model.common.ApiResponse
import model.user.UserInfoResponse
import javax.inject.Inject

class UserInfoDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : BaseDataSource(), UserInfoDataSource {

    override suspend fun getUserInfo(): CustomResult<UserInfoResponse, DataError.Network> = request {
        client.get("/company-service/api/v1/members/my-page").body<ApiResponse<UserInfoResponse>>()
    }
}
