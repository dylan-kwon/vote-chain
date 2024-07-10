package dylan.kwon.votechain.build_logic.convention.plugin.base

import dylan.kwon.votechain.build_logic.convention.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.plugins.PluginManager

open class ProjectPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                onPlugin()
            }
            with(extensions) {
                onExtensions()
            }
            with(dependencies) {
                onDependencies(libs)
            }
            onProject()
        }
    }

    open fun Project.onProject() {}

    open fun PluginManager.onPlugin() {}

    open fun ExtensionContainer.onExtensions() {}

    open fun DependencyHandler.onDependencies(libs: VersionCatalog) {}
}