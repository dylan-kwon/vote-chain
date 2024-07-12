plugins {
    alias(libs.plugins.votechain.android.library)
    alias(libs.plugins.votechain.coroutine)
    alias(libs.plugins.votechain.hilt)
}

android {
    namespace = "dylan.kwon.votechain.core.coroutine.android"
}

dependencies {
    implementation(libs.hilt.android.compiler)
    implementation(libs.hilt.android.testing)
    implementation(libs.kotlinx.coroutines.test)
}