package dylan.kwon.votechain.build_logic.convention.plugin

import com.android.build.gradle.LibraryExtension
import dylan.kwon.votechain.build_logic.convention.common.BuildType
import dylan.kwon.votechain.build_logic.convention.common.buildName
import dylan.kwon.votechain.build_logic.convention.extension.android.addJvmDesugaringDependency
import dylan.kwon.votechain.build_logic.convention.extension.android.configureAndroidCommon
import dylan.kwon.votechain.build_logic.convention.extension.android.configureKotlinJvmTargetInAndroid
import dylan.kwon.votechain.build_logic.convention.extension.android.library.applyAndroidLibraryPlugin
import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.configure

class AndroidLibraryPlugin : ProjectPlugin() {

    override fun Project.onProject() {
        configureKotlinJvmTargetInAndroid()
    }

    override fun PluginManager.onPlugin() {
        applyAndroidLibraryPlugin()
    }

    override fun ExtensionContainer.onExtensions() {
        configure<LibraryExtension> {
            configureAndroidCommon()
            configureAndroidLibrary()
        }
    }

    private fun LibraryExtension.configureAndroidLibrary() {
        configureDefaultConfig()
        configureBuildType()
    }

    private fun LibraryExtension.configureDefaultConfig() {
        defaultConfig {
            consumerProguardFiles("consumer-rules.pro")
        }
    }

    private fun LibraryExtension.configureBuildType() {
        buildTypes {
            BuildType.values().forEach { buildType ->
                getByName(buildType.buildName) {
                    signingConfig = signingConfigs.getByName(buildType.keyStore.name)
                }
            }
        }
    }

    override fun DependencyHandler.onDependencies() {
        addJvmDesugaringDependency(libs)
    }
}