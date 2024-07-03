package com.hobbyloop.member

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HobbyLoopApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val kakaoAppKey = BuildConfig.KAKAO_SDK_KEY
        KakaoSdk.init(this, kakaoAppKey)
    }
}