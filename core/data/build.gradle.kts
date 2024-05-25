plugins {
    id("buildlogic.datamodule")
    id("kotlinx-serialization")
}

android {
    namespace = "com.hobbyloop.data"
}

dependencies {
    implementation(libs.androidx.paging.compose)
}
