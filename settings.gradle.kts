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

rootProject.name = "DoumouniSo"
include(":app")
include(":common:components")
include(":common:theme")
include(":common:futureapi")
include(":data:model")
include(":data:backend")
include(":data:local")
include(":future:home")
include(":future:cart")
include(":future:restaurant")
include(":future:shop")
include(":future:orders")
include(":future:search")
include(":future:profile")

include(":ai")
