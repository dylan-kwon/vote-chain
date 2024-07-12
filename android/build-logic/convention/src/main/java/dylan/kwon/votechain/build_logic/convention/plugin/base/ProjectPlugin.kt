package dylan.kwon.votechain.build_logic.convention.plugin.base

import dylan.kwon.votechain.build_logic.convention.extension.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.plugins.PluginManager

open class ProjectPlugin : Plugin<Project> {

    protected lateinit var target: Project

    protected val libs: VersionCatalog
        get() = target.libs

    val isAndroid: Boolean
        get() = isAndroidApplication || isAndroidLibrary

    val isAndroidApplication: Boolean
        get() = target.plugins.hasPlugin("com.android.application")

    val isAndroidLibrary: Boolean
        get() = target.plugins.hasPlugin("com.android.library")

    val isJvmLibrary: Boolean
        get() = target.plugins.hasPlugin("java-library")

    override fun apply(target: Project) {
        this.target = target

        with(target) {
            with(pluginManager) {
                onPlugin()
            }
            with(extensions) {
                onExtensions()
            }
            with(dependencies) {
                onDependencies()
            }
            onProject()
        }
    }

    open fun Project.onProject() {}

    open fun PluginManager.onPlugin() {}

    open fun ExtensionContainer.onExtensions() {}

    open fun DependencyHandler.onDependencies() {}
}