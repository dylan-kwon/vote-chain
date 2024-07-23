plugins {
    alias(libs.plugins.votechain.android.feature)
}

android {
    namespace = "dylan.kwon.votechain.feature.vote"
}

dependencies {
    implementation(projects.core.ui.navigation)
}