import com.android.build.api.dsl.LibraryExtension
import internal.configureAndroid
import internal.configureCompose
import internal.configureHilt
import internal.configureImageLoading
import internal.configureKotlinExtensions
import internal.configureNavigation
import internal.configureOrbitMvi
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class UiModuleConventions : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
        }

        configureAndroid<LibraryExtension>()
        configureCompose<LibraryExtension>()
        configureNavigation()
        configureKotlinExtensions()
        configureHilt()
        configureImageLoading()
        configureOrbitMvi()

        dependencies{
            add("implementation", project(":core:domain"))
        }
    }
}
