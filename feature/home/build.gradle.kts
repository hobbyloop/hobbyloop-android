plugins {
    id("buildlogic.featuremodule")
}

android {
    namespace = "com.hobbyloopapp.feature.home"
}

dependencies {
    implementation(project(":core:ui"))
}
