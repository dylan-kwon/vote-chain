plugins {
    alias(libs.plugins.votechain.jvm.library)
    alias(libs.plugins.votechain.coroutine)
    alias(libs.plugins.votechain.serialization)
    alias(libs.plugins.votechain.hilt)
}

dependencies {
    api(projects.core.architecture.cleanArchitecture)
    api(libs.paging.common)
}