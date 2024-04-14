plugins {
    id("buildlogic.featuremodule")
}

android {
    namespace = "com.hobboyloopapp.feature.storage"
}

dependencies {
    implementation(project(":core:ui"))
}
