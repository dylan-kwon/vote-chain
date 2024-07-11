package dylan.kwon.votechain.build_logic.convention.extension.android.library

import org.gradle.api.plugins.PluginManager

internal fun PluginManager.applyAndroidLibraryPlugin() {
    apply("com.android.library")
    apply("org.jetbrains.kotlin.android")
}