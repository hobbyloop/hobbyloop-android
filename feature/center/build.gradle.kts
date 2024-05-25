plugins {
    id("buildlogic.featuremodule")
}

android {
    namespace = "com.hobbyloop.feature.center"
}

dependencies {
    implementation(libs.androidx.paging.compose)
    implementation(project(":core:ui"))
}
