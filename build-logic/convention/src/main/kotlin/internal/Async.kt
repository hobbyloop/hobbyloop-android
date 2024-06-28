package internal

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAsync() {
    dependencies {
        add("implementation", getLibrary("kotlinx-coroutines-core"))
    }
}
