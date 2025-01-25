pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Wolt_Walkaround"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":walkaround:data")
include(":walkaround:domain")
include(":walkaround:presentation")
include(":core:data")
include(":core:domain")
include(":core:presentation:designsystem")
include(":core:presentation:ui")
include(":core:database")
include(":walkaround:location")
include(":walkaround:network")
include(":core:test-util")
