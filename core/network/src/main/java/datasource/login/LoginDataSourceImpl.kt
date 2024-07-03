package datasource.login

import com.hobbyloop.domain.entity.login.LoginInfo
import io.ktor.client.HttpClient
import model.login.LoginRequest
import model.login.LoginResponse
import util.HttpClientUtil
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : LoginDataSource {

    override suspend fun login(loginInfo: LoginInfo): LoginResponse {
        val endPoint = "company-service/api/v1/login/members"
        val requestBody = LoginRequest(
            accessToken = loginInfo.accessToken,
            provider = loginInfo.provider
        )

        return HttpClientUtil.postRequest(client, endPoint, requestBody, loginInfo.accessToken)
    }
}