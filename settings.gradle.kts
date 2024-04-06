pluginManagement {
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
    }
}

rootProject.name = "hobbyloopmember"
include(":app")
include(":core:data")
include(":core:database")
include(":core:datastore")
include(":core:network")
include(":core:ui")
include(":core:domain")
include(":feature:login")
include(":feature:signup")
include(":feature:home")
include(":feature:facility")
include(":feature:reservation")
include(":feature:storage")
include(":feature:my-page")
