package dylan.kwon.votechain.build_logic.convention.plugin

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import dylan.kwon.votechain.build_logic.convention.extension.android.configureCompose
import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.configure

class ComposePlugin : ProjectPlugin() {

    override fun PluginManager.onPlugin() {
        apply("org.jetbrains.kotlin.plugin.compose")
    }

    override fun ExtensionContainer.onExtensions() {
        when {
            isAndroidApplication -> configure<ApplicationExtension> {
                configureCompose()
            }

            isAndroidLibrary -> configure<LibraryExtension> {
                configureCompose()
            }

            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    override fun DependencyHandler.onDependencies() {
        val bom = libs.findLibrary("compose-bom").get()

        add("implementation", platform(bom))
        add("androidTestImplementation", platform(bom))

        add("implementation", libs.findLibrary("compose-activity").get())
        add("implementation", libs.findLibrary("compose-ui").get())
        add("implementation", libs.findLibrary("compose-ui-graphics").get())
        add("implementation", libs.findLibrary("compose-ui-tooling-preview").get())
        add("implementation", libs.findLibrary("compose-material3").get())

        add("ksp", libs.findLibrary("compose-destination-ksp").get())
        add("implementation", libs.findLibrary("compose-destination-core").get())
        add("implementation", libs.findLibrary("compose-destination-bottomSheet").get())

        add("debugImplementation", libs.findLibrary("compose-ui-tooling").get())
        add("debugImplementation", libs.findLibrary("compose-ui-test-manifest").get())

        add("androidTestImplementation", libs.findLibrary("compose-ui-test-junit4").get())
    }

}