import com.android.build.api.dsl.LibraryExtension
import internal.configureAndroid
import internal.configureAndroidTest
import internal.configureHilt
import internal.configureHttpNetworking
import internal.configureJsonConverter
import internal.configureKotlinExtensions
import internal.configureUnitTest
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 추후 network 및 data 모듈에 필요한 라이브러리를 의논하여 선정하고, 각 모듈별로 필요한 라이브러리만을 사용할 것
 */
class NetworkModuleConventions : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
        }

        configureAndroid<LibraryExtension>()
        configureHilt()
        configureKotlinExtensions()
        configureAndroidTest()
        configureUnitTest()
        configureHttpNetworking()
        configureJsonConverter()
    }
}
