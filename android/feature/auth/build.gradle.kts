plugins {
    alias(libs.plugins.votechain.android.library)
    alias(libs.plugins.votechain.android.lifecycle)
    alias(libs.plugins.votechain.coroutine)
    alias(libs.plugins.votechain.serialization)
    alias(libs.plugins.votechain.immutableCollections)
    alias(libs.plugins.votechain.hilt)
    alias(libs.plugins.votechain.compose)
}

android {
    namespace = "dylan.kwon.votechain.feature.auth"
}

dependencies {
    implementation(projects.core.domain.useCase)
    implementation(projects.core.ui.designSystem)
    implementation(projects.core.architecture.mvi)
    implementation(projects.core.coroutine.jvm)
    testImplementation(projects.core.coroutine.test)
}