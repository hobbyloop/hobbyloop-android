package model.login

import com.hobbyloop.domain.entity.login.UserLoginResult
import kotlinx.serialization.Serializable

@Serializable
data class NetworkLogin(
    val accessToken: String?,
    val refreshToken: String?,
    val email: String?,
    val provider: String?,
    val subject: String?,
    val oauth2AccessToken: String?
) {
    fun toDomainModel(): UserLoginResult {
        return UserLoginResult(
            accessToken = accessToken,
            refreshToken = refreshToken,
            email = email,
            provider = provider,
            subject = subject,
            oauth2AccessToken = oauth2AccessToken
        )
    }
}

@Serializable
data class LoginResponse(
    val data: NetworkLogin
)