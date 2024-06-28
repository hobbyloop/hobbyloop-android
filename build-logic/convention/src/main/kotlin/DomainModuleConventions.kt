import internal.configureAsync
import internal.configureInject
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
class DomainModuleConventions : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("org.jetbrains.kotlin.jvm")
            apply("java-library")
        }

        configureInject()
        configureAsync()
    }
}
