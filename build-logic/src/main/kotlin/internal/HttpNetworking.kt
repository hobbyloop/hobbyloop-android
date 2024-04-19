package internal

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureHttpNetworking() {
    dependencies {
        add("implementation", getLibrary("ktor-core"))
        add("implementation", getLibrary("ktor-cio"))
        add("implementation", getLibrary("ktor-negotiation"))
        add("implementation", getLibrary("ktor-serialization"))
    }
}
