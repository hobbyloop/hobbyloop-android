package model.signup

import com.hobbyloop.domain.entity.login.UserLoginResult
import com.hobbyloop.domain.entity.signup.UserSignUpResult
import kotlinx.serialization.Serializable

@Serializable
data class NetworkSignUp(
    val accessToken: String,
    val refreshToken: String,
) {
    fun toDomainModel(): UserSignUpResult {
        return UserSignUpResult(
            accessToken = accessToken,
            refreshToken = refreshToken,
        )
    }
}

@Serializable
data class SignUpResponse(
    val data: NetworkSignUp
)