import com.android.build.api.dsl.LibraryExtension
import internal.configureAndroid
import internal.configureAndroidTest
import internal.configureUnitTest
import internal.getLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
class DatabaseModuleConventions : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }

        configureAndroid<LibraryExtension>()
        configureUnitTest()
        configureAndroidTest()

        dependencies {
            add("implementation", getLibrary("core-ktx"))
            add("implementation", getLibrary("androidx-appcompat"))
            add("implementation", getLibrary("material"))
        }
    }
}

