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

rootProject.name = "vote-chain"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":feature:auth")
include(":feature:vote")
include(":feature:crypto-wallet")
include(":core:domain")
include(":core:architecture:mvi")
include(":core:coroutine:test")
include(":core:coroutine:jvm")
include(":core:ui:design-system")
include(":core:architecture:clean-architecture")
include(":core:driver:datastore")
include(":core:coroutine:test-android")
include(":core:adapter:bundle")
include(":core:ui:navigation")
include(":core:driver:web3j")
include(":core:driver:firebase")
include(":core:ui:compose-ext")
include(":core:driver:vote-contract")
include(":feature:settings")
