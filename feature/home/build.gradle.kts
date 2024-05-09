plugins {
    id("buildlogic.featuremodule")
}

android {
    namespace = "com.hobbyloop.feature.home"
}
dependencies {
    implementation(project(":core:ui"))
}
