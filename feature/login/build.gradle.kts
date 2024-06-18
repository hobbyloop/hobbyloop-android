plugins {
    id("buildlogic.featuremodule")
}

android {
    namespace = "com.hobbyloop.feature.login"
}

dependencies {
    implementation("com.kakao.sdk:v2-user:2.12.1")
}
