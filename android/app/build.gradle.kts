plugins {
    alias(libs.plugins.votechain.android.application)
    alias(libs.plugins.votechain.android.parcelize)
    alias(libs.plugins.votechain.android.lifecycle)
    alias(libs.plugins.votechain.coroutine)
    alias(libs.plugins.votechain.serialization)
    alias(libs.plugins.votechain.immutableCollections)
    alias(libs.plugins.votechain.hilt)
    alias(libs.plugins.votechain.compose)
    alias(libs.plugins.votechain.firebase)
}

android {
    namespace = "dylan.kwon.votechain"

    defaultConfig {
        applicationId = "dylan.kwon.votechain"

        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {

    implementation(libs.androidx.splashscreen)

    implementation(projects.feature.auth)
    implementation(projects.feature.vote)
    implementation(projects.feature.cryptoWallet)

    implementation(projects.core.ui.composeExt)
    implementation(projects.core.ui.navigation)
    implementation(projects.core.ui.designSystem)

    implementation(projects.core.architecture.mvi)

    implementation(projects.core.data.bundleDi)
    implementation(projects.core.domain)
}