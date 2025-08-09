pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "myHealthConnect"
include(":app")
include(":core:database")
include(":features:home")
include(":core:data")
include(":core:health-connect")
include(":core:model")
include(":core:common")
include(":core:ui")
include(":features:personal")
include(":core:datastore")
