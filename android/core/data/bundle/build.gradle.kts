plugins {
    alias(libs.plugins.votechain.android.library)
    alias(libs.plugins.votechain.coroutine)
    alias(libs.plugins.votechain.hilt)
}

android {
    namespace = "dylan.kwon.voetchain.core.data.bundle"
}

dependencies {
    implementation(projects.core.domain.port)
    implementation(projects.core.coroutine.jvm)
    implementation(projects.core.data.datastore)
}