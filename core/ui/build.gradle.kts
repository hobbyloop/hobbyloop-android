plugins {
    alias(libs.plugins.buildlogic.uimodule)
}

android {
    namespace = "com.hobbyloop.ui"
}

dependencies {
    api(libs.ui)
    api(libs.ui.tooling)
    api(libs.ui.tooling.preview)
    api(libs.material3)
    implementation("androidx.compose.material:material:1.6.6")
    implementation(project(":core:domain"))
}
