plugins {
    alias(libs.plugins.votechain.android.library)
    alias(libs.plugins.votechain.android.lifecycle)
    alias(libs.plugins.votechain.kotlinx.coroutine)
}

android {
    namespace = "dylan.kwon.votechain.core.architecture.mvi"
}
