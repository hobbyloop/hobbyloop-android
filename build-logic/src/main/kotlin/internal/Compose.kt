package internal

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal inline fun <reified T : CommonExtension<*, *, *, *, *>> Project.configureCompose() {
    extensions.configure<T> {
        buildFeatures {
            compose = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.1"
        }
    }

    dependencies {
        add("implementation", platform(getLibrary("compose-bom")))
        add("implementation", getLibrary("ui"))
        add("implementation", getLibrary("ui-graphics"))
        add("implementation", getLibrary("ui-tooling-preview"))
        add("implementation", getLibrary("material3"))
        add("implementation", getLibrary("material"))
        add("implementation", getLibrary("lifecycle-runtime-ktx"))
        add("implementation", getLibrary("androidx-lifecycle-runtime-compose"))
        add("implementation", getLibrary("activity-compose"))
        add("debugImplementation", getLibrary("ui-tooling"))
    }
}
