plugins {
    id("buildlogic.featuremodule")
}

android {
    namespace = "com.hobbyloopapp.feature.my_page"
}

dependencies {
    implementation(project(":core:ui"))
}
