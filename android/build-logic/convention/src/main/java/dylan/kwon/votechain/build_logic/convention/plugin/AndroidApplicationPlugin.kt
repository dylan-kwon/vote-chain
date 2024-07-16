package dylan.kwon.votechain.build_logic.convention.plugin

import com.android.build.api.dsl.ApplicationExtension
import dylan.kwon.votechain.build_logic.convention.common.BuildType
import dylan.kwon.votechain.build_logic.convention.common.Config
import dylan.kwon.votechain.build_logic.convention.common.ProductFlavor
import dylan.kwon.votechain.build_logic.convention.common.applicationIdSuffix
import dylan.kwon.votechain.build_logic.convention.common.buildName
import dylan.kwon.votechain.build_logic.convention.common.versionNameSuffix
import dylan.kwon.votechain.build_logic.convention.extension.android.addAndroidCommonDependencies
import dylan.kwon.votechain.build_logic.convention.extension.android.application.applyAndroidApplicationPlugin
import dylan.kwon.votechain.build_logic.convention.extension.android.configureAndroidCommon
import dylan.kwon.votechain.build_logic.convention.extension.android.configureKotlinJvmTargetInAndroid
import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.configure

class AndroidApplicationPlugin : ProjectPlugin() {

    override fun Project.onProject() {
        configureKotlinJvmTargetInAndroid()
    }

    override fun PluginManager.onPlugin() {
        applyAndroidApplicationPlugin()
    }

    override fun ExtensionContainer.onExtensions() {
        configure<ApplicationExtension> {
            configureAndroidCommon(target.rootDir)
            configureAndroidApplication()
        }
    }

    private fun ApplicationExtension.configureAndroidApplication() {
        configureDefaultConfig()
        configureProductFlavor()
        configureBuildType()
    }

    private fun ApplicationExtension.configureDefaultConfig() {
        defaultConfig {
            targetSdk = Config.Android.TARGET_SDK
        }
    }

    private fun ApplicationExtension.configureProductFlavor() {
        productFlavors {
            ProductFlavor.values().forEach { flavor ->
                getByName(flavor.buildName) {
                    if (flavor.useSuffix) {
                        versionNameSuffix = flavor.versionNameSuffix
                        applicationIdSuffix = flavor.applicationIdSuffix
                    }
                }
            }
        }
    }

    private fun ApplicationExtension.configureBuildType() {
        buildTypes {
            BuildType.values().forEach { buildType ->
                getByName(buildType.buildName) {
                    isShrinkResources = buildType.isShrinkResources

                    signingConfig = signingConfigs.getByName(buildType.keyStore.buildName)

                    if (buildType.useSuffix) {
                        versionNameSuffix = buildType.versionNameSuffix
                        applicationIdSuffix = buildType.applicationIdSuffix
                    }
                }
            }
        }
    }

    override fun DependencyHandler.onDependencies() {
        addAndroidCommonDependencies(libs)
        add("implementation", libs.findLibrary("androidx-startup-runtime").get())
    }

}