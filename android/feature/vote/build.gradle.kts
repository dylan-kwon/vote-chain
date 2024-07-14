plugins {
    alias(libs.plugins.votechain.android.library)
    alias(libs.plugins.votechain.coroutine)
    alias(libs.plugins.votechain.serialization)
    alias(libs.plugins.votechain.hilt)
    alias(libs.plugins.votechain.compose)
}

android {
    namespace = "dylan.kwon.votechain.feature.vote"
}

dependencies {
    implementation(projects.core.domain)
}