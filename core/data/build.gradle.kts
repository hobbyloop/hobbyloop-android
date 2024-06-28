plugins {
    alias(libs.plugins.buildlogic.datamodule)
    id("kotlinx-serialization")
}

android {
    namespace = "com.hobbyloop.data"
}

dependencies {
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}
