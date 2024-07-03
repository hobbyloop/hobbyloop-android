package com.hobbyloop.domain.repository.user

import com.hobbyloop.domain.entity.user.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {

    val userData: Flow<UserData>
    suspend fun setJwt(jwt: String)
}
