plugins {
    alias(libs.plugins.votechain.jvm.library)
    alias(libs.plugins.votechain.coroutine)
    alias(libs.plugins.votechain.hilt)
}

dependencies {
    implementation(projects.core.coroutine.jvm)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.junit)
}