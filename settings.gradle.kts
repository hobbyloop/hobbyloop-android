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
include(":core:network")
include(":core:datastore")
include(":core:database")
include(":core:domain")
include(":core:ui")

include(":feature")
