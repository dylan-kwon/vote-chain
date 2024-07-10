package dylan.kwon.votechain.build_logic.convention.extension.android.application

import org.gradle.api.plugins.PluginManager

fun PluginManager.applyAndroidApplicationPlugin() {
    apply("com.android.application")
    apply("org.jetbrains.kotlin.android")
}