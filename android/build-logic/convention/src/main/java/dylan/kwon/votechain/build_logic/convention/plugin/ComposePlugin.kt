package dylan.kwon.votechain.build_logic.convention.plugin

import com.android.build.api.dsl.ApplicationExtension
import dylan.kwon.votechain.build_logic.convention.common.Config
import dylan.kwon.votechain.build_logic.convention.extension.android.addJvmDesugaringDependency
import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.kotlin.dsl.configure

class ComposePlugin : ProjectPlugin() {

    override fun ExtensionContainer.onExtensions() {
        configure<ApplicationExtension> {
            buildFeatures {
                compose = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = Config.Compose.COMPILER_VERSION
            }
        }
    }

    override fun DependencyHandler.onDependencies(libs: VersionCatalog) {
        addJvmDesugaringDependency(libs)
    }

}