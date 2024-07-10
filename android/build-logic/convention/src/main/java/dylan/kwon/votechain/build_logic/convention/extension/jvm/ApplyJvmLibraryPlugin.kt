package dylan.kwon.votechain.build_logic.convention.extension.jvm

import org.gradle.api.plugins.PluginManager

fun PluginManager.applyJvmLibraryPlugins() {
    apply("java-library")
    apply("org.jetbrains.kotlin.jvm")
}