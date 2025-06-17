enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TMBD"
include(":androidApp")
include(":core:network")
include(":core:database")
include(":core:model")
include(":core:data")
include(":core:domain")
include(":core:common")
include(":feature:movies")
include(":core:ui")
include(":core:bridge")
include(":feature:details")
