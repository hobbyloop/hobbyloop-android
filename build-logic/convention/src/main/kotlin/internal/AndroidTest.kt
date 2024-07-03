package internal

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidTest() {
    dependencies {
        add("androidTestImplementation", platform(getLibrary("compose-bom")))
        add("androidTestImplementation", getLibrary("androidx-test-ext-junit"))
        add("androidTestImplementation", getLibrary("espresso-core"))
        add("androidTestImplementation", getLibrary("ui-test-junit4"))
    }
}
