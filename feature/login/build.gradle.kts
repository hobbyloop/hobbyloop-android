plugins {
    id("buildlogic.featuremodule")
}

android {
    namespace = "com.hobbyloopapp.feature.login"
}

dependencies {
    implementation(project(":core:ui"))
}
