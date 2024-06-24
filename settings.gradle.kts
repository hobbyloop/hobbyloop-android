/**
 * Android Studio Iguana | 2023.2.1 Canary 7 버전에서 빌드 시 발생하는 문제를 해결하기 위한 코드 추가
 *
 * [이슈]
 * FAILURE: Build failed with an exception.
 * * What went wrong:
 * Unable to make progress running work. There are items queued for execution but none of them can be started
 *
 * [참고]
 * https://stackoverflow.com/questions/77279080/unable-to-make-progress-running-work-android-studio
 */
gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:testClasses"))

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        includeBuild("build-logic")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("https://devrepo.kakao.com/nexus/content/groups/public/") }
    }
}

rootProject.name = "hobbyloopmember"
include(":app")

include(":core:data")
include(":core:network")
include(":core:datastore")
include(":core:database")
include(":core:domain")
include(":core:ui")
include(":core:common")

include(":feature:login")
include(":feature:sign-up")
include(":feature:home")
include(":feature:center")
include(":feature:reservation")
include(":feature:schedule")
include(":feature:my-page")
include(":feature:center-detail")
