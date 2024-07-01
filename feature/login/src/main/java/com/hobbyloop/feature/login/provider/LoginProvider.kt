package com.hobbyloop.feature.login.provider

import android.content.Context
import com.hobbyloop.feature.login.model.AuthToken

interface LoginProvider {
    fun login(context : Context,callback: (AuthToken?, Throwable?) -> Unit)

}


