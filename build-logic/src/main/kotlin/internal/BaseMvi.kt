package internal

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureOrbitMvi() {
    dependencies {
        add("implementation", getLibrary("orbit-core"))
        add("implementation", getLibrary("orbit-viewmodel"))
    }
}