plugins {
    alias(libs.plugins.votechain.jvm.library)
    alias(libs.plugins.votechain.kotlinx.coroutine)
    alias(libs.plugins.votechain.hilt)
}

dependencies {
    implementation(libs.web3j)
    implementation(libs.kotlinx.coroutines.rx2)
}