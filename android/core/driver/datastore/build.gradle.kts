plugins {
    alias(libs.plugins.votechain.android.library)
    alias(libs.plugins.votechain.datastore)
    alias(libs.plugins.votechain.kotlinx.coroutine)
    alias(libs.plugins.votechain.hilt)
}

android {
    namespace = "dylan.kwon.votechain.core.driver.datastore"
}

dependencies {
    implementation(libs.datastore.preferences.crypto)
}