plugins {
    alias(libs.plugins.buildlogic.uimodule)
}

android {
    namespace = "com.hobbyloop.ui"
}

dependencies {
    implementation("androidx.compose.material:material:1.6.6")
}
