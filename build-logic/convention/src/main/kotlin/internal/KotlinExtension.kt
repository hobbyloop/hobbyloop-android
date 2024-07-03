package internal

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureKotlinExtensions() {
    dependencies {
        add("implementation", getLibrary("kotlinx-collections-immutable"))
    }
}
