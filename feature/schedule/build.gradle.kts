plugins {
    id("buildlogic.featuremodule")
}

android {
    namespace = "com.hobbyloop.feature.schedule"
}

dependencies {
    implementation(project(":core:data"))
}
