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

plugins {
    id("de.fayard.refreshVersions") version "0.60.5"
}

refreshVersions {
    rejectVersionIf {
        candidate.stabilityLevel.isLessStableThan(current.stabilityLevel)
    }
    file("build/tmp/refreshVersions").mkdirs()
    versionsPropertiesFile = file("build/tmp/refreshVersions/versions.properties")
}

rootProject.name = "VoteChain"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")

include(":feature:auth")
include(":feature:vote")
include(":feature:crypto-wallet")

include(":core:domain:entity")
include(":core:domain:use-case")

