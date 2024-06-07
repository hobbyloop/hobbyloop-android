package com.hobbyloop.feature.login.provider

import android.content.Context
import com.hobbyloop.feature.login.model.AuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

class KakaoLoginProvider : LoginProvider {
    override fun login(context: Context, callback: (AuthToken?, Throwable?) -> Unit) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    } else {
                        UserApiClient.instance.loginWithKakaoAccount(context) { accountToken, accountError ->
                            if (accountError != null) {
                                callback(null, accountError)
                            } else if (accountToken != null) {
                                callback(
                                    AuthToken(
                                        accountToken.accessToken,
                                        accountToken.refreshToken
                                    ), null
                                )
                            }
                        }
                    }
                } else if (token != null) {
                    callback(AuthToken(token.accessToken, token.refreshToken), null)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context) { token, error ->
                if (error != null) {
                    callback(null, error)
                } else if (token != null) {
                    callback(AuthToken(token.accessToken, token.refreshToken), null)
                }
            }
        }
    }

}
