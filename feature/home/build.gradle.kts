plugins {
    id("buildlogic.featuremodule")
}

android {
    namespace = "com.hobbyloop.feature.home"
}
dependencies {
    implementation(project(":core:ui"))
    // Orbit MVI dependencies
    implementation("org.orbit-mvi:orbit-core:4.3.2")
    implementation("org.orbit-mvi:orbit-viewmodel:4.3.2")
    implementation("org.orbit-mvi:orbit-viewmodel:4.3.2")
    implementation("com.google.accompanist:accompanist-pager:0.28.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.28.0")
}
