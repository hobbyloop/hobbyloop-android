package internal

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureInject() {
    dependencies {
        add("implementation", getLibrary("inject"))
    }
}
