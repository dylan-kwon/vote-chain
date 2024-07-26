plugins {
    alias(libs.plugins.votechain.jvm.library)
    alias(libs.plugins.votechain.kotlinx.coroutine)
    alias(libs.plugins.votechain.kotlinx.serialization)
    alias(libs.plugins.votechain.hilt)
}

dependencies {
    api(projects.core.architecture.cleanArchitecture)
}