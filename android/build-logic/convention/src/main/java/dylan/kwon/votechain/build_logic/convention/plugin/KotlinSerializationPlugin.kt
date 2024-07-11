package dylan.kwon.votechain.build_logic.convention.plugin

import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginManager

class KotlinSerializationPlugin : ProjectPlugin() {

    override fun PluginManager.onPlugin() {
//        apply("org.jetbrains.kotlin.plugin.serialization")
    }

    override fun DependencyHandler.onDependencies() {
        add("implementation", libs.findLibrary("kotlinx-serialization-json").get())
    }
}