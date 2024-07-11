plugins {
    alias(libs.plugins.votechain.jvm.library)
    alias(libs.plugins.votechain.hilt)
}

dependencies {
    api(projects.core.domain.entity)
}