plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.hobbyloopapp.core.ui"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hobbyloopapp.core.ui"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.ui)
    implementation(libs.bundles.navigation)
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.androidTest)
    debugImplementation(libs.bundles.debugImple)
}
