plugins {
    alias(libs.plugins.votechain.android.library)
    alias(libs.plugins.votechain.hilt)
    alias(libs.plugins.votechain.coroutine)
}

android {
    namespace = "dylan.kwon.votechain.core.driver.firebase"
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)
    implementation(libs.firebase.config)
    implementation(libs.firebase.storage)
    api(libs.firebase.firestore)
}