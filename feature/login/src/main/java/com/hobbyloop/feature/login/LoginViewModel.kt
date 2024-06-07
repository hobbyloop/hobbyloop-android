package com.hobbyloop.feature.login

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.hobbyloop.feature.login.model.LoginProviderType
import com.hobbyloop.feature.login.provider.GoogleLoginProvider
import com.hobbyloop.feature.login.provider.KakaoLoginProvider
import com.hobbyloop.feature.login.provider.NaverLoginProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    fun login(context: Context, providerType: LoginProviderType) {
        val provider = when (providerType) {
            LoginProviderType.KAKAO -> KakaoLoginProvider()
            LoginProviderType.NAVER -> NaverLoginProvider()
            LoginProviderType.GOOGLE -> GoogleLoginProvider()
        }

        provider.login(context) { token, error ->
            if (error != null) {
                Log.e(TAG, "로그인 실패 $error")
            } else if (token != null) {
                Log.e(TAG, "로그인 성공 ${token.accessToken} ${token.refreshToken}")
            }
        }
    }
}
