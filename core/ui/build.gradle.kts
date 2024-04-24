plugins {
    id("buildlogic.uimodule")
}

android {
    namespace = "com.hobbyloop.ui"
}

dependencies {
    api(libs.ui)
    api(libs.ui.tooling)
    api(libs.ui.tooling.preview)
    api(libs.material3)
}
