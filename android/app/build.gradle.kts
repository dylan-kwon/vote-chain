plugins {
    alias(libs.plugins.votechain.android.application)
    alias(libs.plugins.votechain.coroutine)
    alias(libs.plugins.votechain.serialization)
    alias(libs.plugins.votechain.hilt)
    alias(libs.plugins.votechain.compose)
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
    implementation(projects.core.domain.useCase)

    implementation(projects.feature.auth)
    implementation(projects.feature.vote)
    implementation(projects.feature.cryptoWallet)
}