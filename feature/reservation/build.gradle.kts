plugins {
    id("buildlogic.featuremodule")
}

android {
    namespace = "com.hobbyloopapp.feature.reservation"
}

dependencies {
    implementation(project(":core:ui"))
}
