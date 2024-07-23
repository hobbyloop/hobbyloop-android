package datasource

import com.hobbyloop.domain.common.CustomResult
import com.hobbyloop.domain.common.DataError
import io.ktor.client.plugins.ResponseException
import model.common.ApiResponse

abstract class BaseDataSource {
    protected suspend fun <T> request(call: suspend () -> ApiResponse<T>): CustomResult<T, DataError.Network> = try {
        val response = call()
        CustomResult.Success(response.data)
    } catch (e: ResponseException) {
        val networkError = when (e.response.status.value) {
            400 -> DataError.Network.CLIENT_ERROR
            401 -> DataError.Network.UNAUTHORIZED
            404 -> DataError.Network.UNKNOWN
            500 -> DataError.Network.SERVER_ERROR
            else -> DataError.Network.UNKNOWN
        }
        CustomResult.Error(networkError)
    } catch (e: Exception) {
        CustomResult.Error(DataError.Network.UNKNOWN)
    }
}