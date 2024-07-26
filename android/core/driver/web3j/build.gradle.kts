plugins {
    alias(libs.plugins.votechain.android.library)
    alias(libs.plugins.votechain.hilt)
    alias(libs.plugins.votechain.kotlinx.coroutine)
    alias(libs.plugins.votechain.kotlinx.serialization)
}

android {
    namespace = "dylan.kwon.votechain.core.driver.web3j"
}

dependencies {
    implementation(libs.web3j)
}