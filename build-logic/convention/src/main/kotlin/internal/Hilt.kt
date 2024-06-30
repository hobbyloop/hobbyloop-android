package internal

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureHilt() {
    with(pluginManager) {
        apply("dagger.hilt.android.plugin")
        apply("org.jetbrains.kotlin.kapt")
    }

    dependencies {
        add("implementation", getLibrary("hilt"))
        add("kapt", getLibrary("hilt.compiler"))
    }
}