package internal

import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureConvention() {
    dependencies {
        add("lintChecks", getLibrary("compose-lint-checks"))
    }
}
