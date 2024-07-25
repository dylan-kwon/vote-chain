plugins {
    alias(libs.plugins.votechain.android.library)
    alias(libs.plugins.votechain.datastore)
    alias(libs.plugins.votechain.coroutine)
    alias(libs.plugins.votechain.hilt)
}

android {
    namespace = "dylan.kwon.votechain.core.driver.datastore"
}

dependencies {
    implementation(projects.core.coroutine.jvm)
    implementation(libs.datastore.preferences.crypto)
}