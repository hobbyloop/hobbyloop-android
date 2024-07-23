package datasource.user

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import model.user.UserInfoResponse

interface UserInfoDataSource {
    suspend fun getUserInfo(): CustomResult<UserInfoResponse, DataError.Network>
}
