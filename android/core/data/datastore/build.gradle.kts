plugins {
    alias(libs.plugins.votechain.android.library)
    alias(libs.plugins.votechain.datastore)
    alias(libs.plugins.votechain.coroutine)
    alias(libs.plugins.votechain.hilt)
}

android {
    namespace = "dylan.kwon.votechain.core.data.datastore"
}

dependencies {
    implementation(projects.core.coroutine.jvm)
    implementation(projects.core.crypto)
}