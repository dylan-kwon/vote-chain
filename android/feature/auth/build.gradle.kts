plugins {
    alias(libs.plugins.votechain.android.feature)
}

android {
    buildTypes {
        release {
            proguardFiles()
        }
    }
}

android {
    namespace = "dylan.kwon.votechain.feature.auth"
}