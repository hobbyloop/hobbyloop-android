import com.android.build.api.dsl.LibraryExtension
import internal.configureAndroid
import internal.configureAndroidTest
import internal.configureHilt
import internal.configureKotlinExtensions
import internal.configureUnitTest
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DataModuleConventions : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
        }

        configureAndroid<LibraryExtension>()
        configureHilt()
        configureKotlinExtensions()
        configureAndroidTest()
        configureUnitTest()

        dependencies{
            add("implementation", project(":core:domain"))
            add("implementation", project(":core:network"))
            add("implementation", project(":core:datastore"))
        }
    }
}
