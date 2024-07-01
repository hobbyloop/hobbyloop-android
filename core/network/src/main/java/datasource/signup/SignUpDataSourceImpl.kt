package datasource.signup

import com.hobbyloop.domain.entity.signup.SignUpInfo
import io.ktor.client.HttpClient
import model.signup.SignUpRequest
import model.signup.SignUpResponse
import util.HttpClientUtil
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignUpDataSourceImpl @Inject constructor(
    private val client: HttpClient
) : SignUpDataSource {
    override suspend fun signup(signUpInfo: SignUpInfo): SignUpResponse {
        val endPoint = "/company-service/api/v1/join"
        val requestBody = SignUpRequest(
            name = signUpInfo.name,
            email =  signUpInfo.email,
            nickname = signUpInfo.nickname,
            gender = signUpInfo.gender,
            birthday = signUpInfo.birthday,
            phoneNumber = signUpInfo.phoneNumber,
            isOption1 = signUpInfo.isOption1,
            isOption2 = signUpInfo.isOption2,
            provider = signUpInfo.provider,
            subject = signUpInfo.subject,
            oauth2AccessToken = signUpInfo.oauth2AccessToken,
            ci = signUpInfo.ci,
            di = signUpInfo.di
        )

        return HttpClientUtil.postRequest(client, endPoint, requestBody, signUpInfo.oauth2AccessToken)
    }
}