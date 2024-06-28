import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import internal.configureAndroid
import internal.configureAndroidTest
import internal.configureCompose
import internal.configureConvention
import internal.configureHilt
import internal.configureKotlinExtensions
import internal.configureNavigation
import internal.configureUnitTest
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import java.util.Properties

class AppModuleConventions : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.application")
            apply("org.jlleitschuh.gradle.ktlint")
        }

        val localProperties = Properties().apply {
            val localPropertiesFile = rootProject.file("local.properties")
            if (localPropertiesFile.exists()) {
                localPropertiesFile.inputStream().use { load(it) }
            }
        }

        val kakaoSdkKey: String = localProperties.getProperty("KAKAO_SDK_KEY", "")

        extensions.configure<BaseAppModuleExtension> {
            compileSdk = 34

            defaultConfig {
                applicationId = "com.hobbyloop.member"
                minSdk = 21
                targetSdk = 34
                versionCode = 1
                versionName = "1.0"

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                vectorDrawables {
                    useSupportLibrary = true
                }

                buildConfigField("String", "KAKAO_SDK_KEY", "\"$kakaoSdkKey\"")
                manifestPlaceholders["kakaoScheme"] = "kakao$kakaoSdkKey"
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
            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

            buildFeatures {
                buildConfig = true
            }

            namespace = "com.hobbyloop.member"
        }

        configureAndroid<BaseAppModuleExtension>()
        configureCompose<BaseAppModuleExtension>()
        configureNavigation()
        configureHilt()
        configureKotlinExtensions()
        configureAndroidTest()
        configureUnitTest()
        configureConvention()
    }
}
