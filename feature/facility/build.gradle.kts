plugins {
    id("buildlogic.featuremodule")
}

android {
    namespace = "com.hobbyloopapp.feature.facility"
}

dependencies {
    implementation(project(":core:ui"))
}
