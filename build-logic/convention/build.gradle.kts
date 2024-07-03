plugins {
    `kotlin-dsl`
}

group = "com.hobbyloop.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.tools.build.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("appModule") {
            id = "buildlogic.appmodule"
            implementationClass = "AppModuleConventions"
        }
        register("dataModule") {
            id = "buildlogic.datamodule"
            implementationClass = "DataModuleConventions"
        }
        register("featureModule") {
            id = "buildlogic.featuremodule"
            implementationClass = "FeatureModuleConventions"
        }
        register("uiModule") {
            id = "buildlogic.uimodule"
            implementationClass = "UiModuleConventions"
        }
        register("networkModule") {
            id = "buildlogic.networkmodule"
            implementationClass = "NetworkModuleConventions"
        }
        register("dataStoreModule") {
            id = "buildlogic.dataStoreModule"
            implementationClass = "DataStoreModuleConventions"
        }
        register("commonModule") {
            id = "buildlogic.commonModule"
            implementationClass = "CommonModuleConventions"
        }
        register("domainModule") {
            id = "buildlogic.domainModule"
            implementationClass = "DomainModuleConventions"
        }
        register("databaseModule") {
            id = "buildlogic.databaseModule"
            implementationClass = "DatabaseModuleConventions"
        }
    }
}
