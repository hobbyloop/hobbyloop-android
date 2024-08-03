package internal

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureJsonConverter() {
    with(pluginManager) {
        apply("kotlinx-serialization")
    }

    dependencies {
        add("api", getLibrary("kotlinx-serialization-json"))
    }
}
