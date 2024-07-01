package com.hobbyloop.feature.login

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hobbyloop.domain.entity.login.LoginInfo
import com.hobbyloop.domain.entity.login.UserLoginResult
import com.hobbyloop.domain.usecase.login.GetJWTUseCase
import com.hobbyloop.domain.usecase.user.SetUserDataUseCase
import com.hobbyloop.feature.login.model.LoginProviderType
import com.hobbyloop.feature.login.provider.GoogleLoginProvider
import com.hobbyloop.feature.login.provider.KakaoLoginProvider
import com.hobbyloop.feature.login.provider.NaverLoginProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val setUserDataUseCase: SetUserDataUseCase,
    private val getJWTUseCase: GetJWTUseCase
) : ViewModel() {

    fun login(context: Context, providerType: LoginProviderType, onSignUpClick: (UserLoginResult) -> Unit) {
        val provider = when (providerType) {
            LoginProviderType.KAKAO -> KakaoLoginProvider()
            LoginProviderType.NAVER -> NaverLoginProvider()
            LoginProviderType.GOOGLE -> GoogleLoginProvider()
        }

        provider.login(context) { token, error ->
            if (error != null) {
                Log.e(TAG, "로그인 실패 $error")
            } else if (token != null) {
                viewModelScope.launch {
                    try {
                        val loginInfo = LoginInfo(token.accessToken, providerType.providerName)
                        val loginResponse = getJWTUseCase(loginInfo)
                        val jwt = loginResponse.accessToken

                        if (jwt == null) {
                            onSignUpClick(loginResponse)
                        } else {
                            setUserDataUseCase.setJwt(jwt)
                        }

                    } catch (e: Exception) {
                        Log.e(TAG, "JWT 획득 실패: ${e.message}")
                    }
                }
            }
        }
    }
}
