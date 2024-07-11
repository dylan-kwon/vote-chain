plugins {
    alias(libs.plugins.votechain.android.application)
    alias(libs.plugins.votechain.hilt)
    alias(libs.plugins.votechain.compose)
}

android {
    namespace = "dylan.kwon.votechain"

    defaultConfig {
        applicationId = "dylan.kwon.votechain"

        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(projects.core.domain.useCase)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}