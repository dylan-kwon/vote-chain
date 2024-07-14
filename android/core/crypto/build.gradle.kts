plugins {
    alias(libs.plugins.votechain.android.library)
}

android {
    namespace = "dylan.kwon.votechain.core.crypto"
}

dependencies {
    api(libs.datastore.preferences.crypto)
}