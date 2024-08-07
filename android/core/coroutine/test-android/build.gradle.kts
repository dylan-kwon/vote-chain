plugins {
    alias(libs.plugins.votechain.android.library)
    alias(libs.plugins.votechain.kotlinx.coroutine)
    alias(libs.plugins.votechain.hilt)
}

android {
    namespace = "dylan.kwon.votechain.core.coroutine.android_test"
}

dependencies {
    implementation(libs.junit)
    implementation(libs.hilt.android.testing)
    implementation(libs.kotlinx.coroutines.test)
}