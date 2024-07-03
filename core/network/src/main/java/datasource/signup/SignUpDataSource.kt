package datasource.signup

import com.hobbyloop.domain.entity.login.LoginInfo
import com.hobbyloop.domain.entity.signup.SignUpInfo
import model.login.LoginResponse
import model.signup.SignUpResponse

interface SignUpDataSource {
    suspend fun signup(signupInfo: SignUpInfo): SignUpResponse
}