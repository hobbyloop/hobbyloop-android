plugins {
    id("buildlogic.featuremodule")
}

android {
    namespace = "com.hobbyloop.feature.signup"
}

dependencies {
    implementation("com.github.KDW03:IOS_DATEPICKER:1.0.3")
    implementation(libs.androidx.lifecycle.runtime.compose)
}
