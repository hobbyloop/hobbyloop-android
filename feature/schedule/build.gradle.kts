plugins {
    id("buildlogic.featuremodule")
}

android {
    namespace = "com.hobbyloop.feature.schedule"
}

dependencies {
    implementation(project(":feature:reservation"))

    // orbit
    implementation(libs.orbit.core)
    implementation(libs.orbit.compose)
    implementation(libs.orbit.viewmodel)
}
