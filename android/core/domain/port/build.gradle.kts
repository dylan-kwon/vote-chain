plugins {
    alias(libs.plugins.votechain.jvm.library)
    alias(libs.plugins.votechain.coroutine)
}

dependencies {
    api(projects.core.domain.entity)
}