import com.android.build.api.dsl.LibraryExtension
import internal.configureAndroid
import internal.configureAndroidTest
import internal.configureHilt
import internal.configureUnitTest
import org.gradle.api.Plugin
import org.gradle.api.Project

class CommonModuleConventions : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }

        configureAndroid<LibraryExtension>()
        configureHilt()
        configureAndroidTest()
        configureUnitTest()
    }
}
