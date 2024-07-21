plugins {
    alias(libs.plugins.votechain.android.library)
    alias(libs.plugins.votechain.hilt)
    alias(libs.plugins.votechain.coroutine)
    alias(libs.plugins.votechain.serialization)
}

android {
    namespace = "dylan.kwon.votechain.core.data.web3j"
}

dependencies {
    implementation(libs.web3j)
}