plugins {
    alias(libs.plugins.votechain.android.library)
    alias(libs.plugins.votechain.kotlinx.coroutine)
    alias(libs.plugins.votechain.hilt)
}

android {
    namespace = "dylan.kwon.voetchain.core.adapter"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.coroutine.jvm)
    implementation(projects.core.driver.datastore)
    implementation(projects.core.driver.firebase)
    implementation(projects.core.driver.web3j)
    implementation(projects.core.driver.voteContract)
    implementation(libs.web3j)
}