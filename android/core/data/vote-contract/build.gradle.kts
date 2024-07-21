plugins {
    alias(libs.plugins.votechain.jvm.library)
    alias(libs.plugins.votechain.coroutine)
    alias(libs.plugins.votechain.hilt)
}

dependencies {
    implementation(libs.web3j)
}