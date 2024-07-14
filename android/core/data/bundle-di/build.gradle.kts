plugins {
    alias(libs.plugins.votechain.android.library)
    alias(libs.plugins.votechain.hilt)
}

android {
    namespace = "dylan.kwon.votechain.core.data.bundle_di"
}

dependencies {
    implementation(projects.core.data.bundle)
    implementation(projects.core.domain)
}