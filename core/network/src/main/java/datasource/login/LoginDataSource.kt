package datasource.login

import com.hobbyloop.domain.entity.login.LoginInfo
import model.login.LoginResponse

interface LoginDataSource {
    suspend fun login(loginInfo: LoginInfo): LoginResponse

}