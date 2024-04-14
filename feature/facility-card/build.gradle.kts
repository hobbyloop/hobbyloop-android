plugins {
    id("buildlogic.featuremodule")
}

android {
    namespace = "com.hobbyloopapp.feature.facility_card"
}

dependencies {
    implementation(project(":core:ui"))
}
