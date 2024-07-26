plugins {
    alias(libs.plugins.votechain.android.library)
    alias(libs.plugins.votechain.coroutine)
    alias(libs.plugins.votechain.hilt)
}

android {
    namespace = "dylan.kwon.votechain.core.coroutine.android_test"
}

dependencies {
    implementation(projects.core.coroutine.jvm)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.hilt.android.testing)
    implementation(libs.junit)
}