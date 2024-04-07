import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

 @Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.hobbyloop.member"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hobbyloop.member"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

ktlint {
    android = true
    ignoreFailures = false
    reporters {
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.CHECKSTYLE)
        reporter(ReporterType.SARIF)
    }
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.ui)
    implementation(libs.bundles.hilt)
    implementation(project(":feature:facility-card"))
    kapt(libs.bundles.hiltCompiler)
    implementation(libs.bundles.navigation)
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.androidTest)
    debugImplementation(libs.bundles.debugImple)

    implementation(project(":feature:login"))
    implementation(project(":feature:signup"))
    implementation(project(":feature:home"))
    implementation(project(":feature:facility"))
    implementation(project(":feature:reservation"))
    implementation(project(":feature:storage"))
    implementation(project(":feature:my-page"))

    lintChecks(libs.compose.lint.checks)
}
