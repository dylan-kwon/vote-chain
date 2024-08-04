plugins {
    alias(libs.plugins.votechain.android.application)
    alias(libs.plugins.votechain.android.parcelize)
    alias(libs.plugins.votechain.android.lifecycle)
    alias(libs.plugins.votechain.kotlinx.coroutine)
    alias(libs.plugins.votechain.kotlinx.serialization)
    alias(libs.plugins.votechain.kotlinx.immutableCollections)
    alias(libs.plugins.votechain.hilt)
    alias(libs.plugins.votechain.android.compose)
    alias(libs.plugins.votechain.firebase)
}

android {
    namespace = "dylan.kwon.votechain"

    defaultConfig {
        applicationId = "dylan.kwon.votechain"

        versionCode = 1
        versionName = "1.0.0"
    }
}

dependencies {

    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.startup.runtime)

    implementation(projects.feature.auth)
    implementation(projects.feature.vote)
    implementation(projects.feature.cryptoWallet)
    implementation(projects.feature.settings)

    implementation(projects.core.ui.composeExt)
    implementation(projects.core.ui.navigation)
    implementation(projects.core.ui.designSystem)

    implementation(projects.core.adapter)
    implementation(projects.core.domain)

    implementation(projects.core.architecture.mvi)
}