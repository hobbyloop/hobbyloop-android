package internal

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureImageLoading() {
    dependencies {
        add("implementation", getLibrary("coil-compose"))
    }
}
