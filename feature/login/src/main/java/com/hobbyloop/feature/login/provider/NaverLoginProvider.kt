package com.hobbyloop.feature.login.provider

import android.content.Context
import com.hobbyloop.feature.login.model.AuthToken

class NaverLoginProvider : LoginProvider {
    override fun login(context: Context, callback: (AuthToken?, Throwable?) -> Unit) {
    }
}