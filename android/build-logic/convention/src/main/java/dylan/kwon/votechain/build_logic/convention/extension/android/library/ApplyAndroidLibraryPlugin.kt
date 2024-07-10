package dylan.kwon.votechain.build_logic.convention.extension.android.library

import org.gradle.api.plugins.PluginManager

fun PluginManager.applyAndroidLibraryPlugin() {
    apply("com.android.library")
    apply("org.jetbrains.kotlin.android")
}