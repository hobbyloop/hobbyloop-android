import com.android.build.api.dsl.LibraryExtension
import internal.configureAndroid
import internal.configureAndroidTest
import internal.configureCompose
import internal.configureHilt
import internal.configureImageLoading
import internal.configureKotlinExtensions
import internal.configureNavigation
import internal.configureOrbitMvi
import internal.configureUnitTest
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FeatureModuleConventions : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
        }

        configureAndroid<LibraryExtension>()
        configureCompose<LibraryExtension>()
        configureNavigation()
        configureKotlinExtensions()
        configureHilt()
        configureAndroidTest()
        configureUnitTest()
        configureImageLoading()
        configureOrbitMvi()


        dependencies{
            add("implementation", project(":core:ui"))
            add("implementation", project(":core:domain"))
        }
    }
}
