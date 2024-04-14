plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.hobbyloopapp.core.database"
    compileSdk = 34
}

dependencies {
    /**
     * 추후 라이브러리 의존성 관리를 build-logic 모듈에서 하도록 함
     */
}
