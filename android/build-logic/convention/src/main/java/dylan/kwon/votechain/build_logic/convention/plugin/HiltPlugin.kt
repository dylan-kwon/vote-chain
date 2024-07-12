package dylan.kwon.votechain.build_logic.convention.plugin

import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginManager

class HiltPlugin : ProjectPlugin() {

    override fun PluginManager.onPlugin() {
        apply("com.google.devtools.ksp")

        if (isAndroid) {
            apply("dagger.hilt.android.plugin")
        }
    }

    override fun DependencyHandler.onDependencies() {
        add("ksp", libs.findLibrary("hilt-compiler").get())
        add("implementation", libs.findLibrary("hilt-core").get())

        if (isAndroid) {
            add("ksp", libs.findLibrary("hilt-android-compiler").get())
            add("implementation", libs.findLibrary("hilt-android").get())

            add("kspTest", libs.findLibrary("hilt-android-compiler").get())
            add("kspAndroidTest", libs.findLibrary("hilt-android-compiler").get())
            add("testImplementation", libs.findLibrary("hilt-android-testing").get())
            add("androidTestAnnotationProcessor", libs.findLibrary("hilt-android-testing").get())
        }
    }
}