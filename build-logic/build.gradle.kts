plugins {
    `kotlin-dsl`
}

group = "com.hobbyloopapp.buildlogic"

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
    }
}
