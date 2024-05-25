pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
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

include(":feature:login")
include(":feature:sign-up")
include(":feature:home")
include(":feature:center")
include(":feature:reservation")
include(":feature:schedule")
include(":feature:my-page")
include(":feature:center-detail")
include(":feature:reservation-detail")
