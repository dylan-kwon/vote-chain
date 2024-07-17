plugins {
    alias(libs.plugins.votechain.android.library)
    alias(libs.plugins.votechain.hilt)
    alias(libs.plugins.votechain.coroutine)
}

android {
    namespace = "dylan.kwon.votechain.core.data.firebase"
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
}