package dylan.kwon.votechain.build_logic.convention.plugin

import dylan.kwon.votechain.build_logic.convention.extension.jvm.addJvmLibraryCommonDependencies
import dylan.kwon.votechain.build_logic.convention.extension.jvm.applyJvmLibraryPlugins
import dylan.kwon.votechain.build_logic.convention.extension.jvm.configureJavaVersionInJvm
import dylan.kwon.votechain.build_logic.convention.extension.jvm.configureKotlinJvmTargetInJvm
import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.plugins.PluginManager

class JvmLibraryPlugin : ProjectPlugin() {

    override fun PluginManager.onPlugin() {
        applyJvmLibraryPlugins()
    }

    override fun ExtensionContainer.onExtensions() {
        configureJavaVersionInJvm()
        configureKotlinJvmTargetInJvm()
    }

    override fun DependencyHandler.onDependencies() {
        addJvmLibraryCommonDependencies(libs)
    }
}