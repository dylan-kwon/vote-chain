plugins {
    alias(libs.plugins.votechain.android.library)
    alias(libs.plugins.votechain.android.compose)

}

android {
    namespace = "dylan.kwon.votechain.core.ui.design_system"
}

dependencies {
    implementation(libs.timeago)
}