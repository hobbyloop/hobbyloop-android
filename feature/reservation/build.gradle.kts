plugins {
    id("buildlogic.featuremodule")
}

android {
    namespace = "com.hobbyloop.feature.reservation"
}

dependencies {
    // orbit
    implementation(libs.orbit.core)
    implementation(libs.orbit.compose)
    implementation(libs.orbit.viewmodel)
}
