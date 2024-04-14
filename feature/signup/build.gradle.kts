plugins {
    id("buildlogic.featuremodule")
}

android {
    namespace = "com.hobbyloopapp.feature.signup"
}

dependencies {
    implementation(project(":core:ui"))
}
