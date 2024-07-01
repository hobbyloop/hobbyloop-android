package com.hobbyloop.datastore

import androidx.datastore.core.DataStore
import com.hobbyloop.core.datastore.UserPreferences
import com.hobbyloop.core.datastore.copy
import com.hobbyloop.domain.entity.user.UserData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HbPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>,
) {

    val userData = userPreferences.data
        .map {
            UserData(
                jwt = it.jwtToken
            )
        }

    suspend fun setJwt(jwt: String) {
        userPreferences.updateData {
            it.copy {
                this.jwtToken = jwt
            }
        }
    }
}