import com.android.build.api.dsl.LibraryExtension
import internal.configureAndroid
import internal.configureHilt
import internal.configureUnitTest
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class DataStoreModuleConventions : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }

        configureAndroid<LibraryExtension>()
        configureHilt()
        configureUnitTest()

        dependencies {
            add("implementation", project(":core:common"))
            add("androidTestImplementation", kotlin("test"))
            add("testImplementation", kotlin("test"))
        }
    }
}
