package com.hobbyloop.data.repository.remote.user

import com.hobbyloop.datastore.HbPreferencesDataSource
import com.hobbyloop.domain.entity.user.UserData
import com.hobbyloop.domain.repository.user.UserDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepositoryImpl @Inject constructor(
    private val preferencesDataSource: HbPreferencesDataSource,
) : UserDataRepository {

    override val userData: Flow<UserData> =
        preferencesDataSource.userData

    override suspend fun setJwt(jwt: String) {
        preferencesDataSource.setJwt(jwt)
    }
}