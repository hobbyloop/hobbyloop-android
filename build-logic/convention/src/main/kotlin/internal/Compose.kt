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
        dependencies {
            add("api", platform(getLibrary("compose-bom")))
            add("api", getLibrary("ui"))
            add("api", getLibrary("ui-graphics"))
            add("api", getLibrary("ui-tooling-preview"))
            add("api", getLibrary("material3"))
            add("api", getLibrary("material"))
            add("api", getLibrary("lifecycle-runtime-ktx"))
            add("api", getLibrary("androidx-lifecycle-runtime-compose"))
            add("api", getLibrary("activity-compose"))
            add("debugImplementation", getLibrary("ui-tooling"))
        }
    }
}
