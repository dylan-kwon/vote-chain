package dylan.kwon.votechain.build_logic.convention.plugin

import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginManager

class FirebasePlugin : ProjectPlugin() {

    override fun PluginManager.onPlugin() {
        apply("com.google.gms.google-services")
        apply("com.google.firebase.crashlytics")
        apply("com.google.firebase.firebase-perf")
    }

    override fun DependencyHandler.onDependencies() {
        val bom = libs.findLibrary("firebase-bom").get()

        add("implementation", platform(bom))
        add("implementation", libs.findLibrary("firebase-auth").get())
        add("implementation", libs.findLibrary("firebase-firestore").get())
        add("implementation", libs.findLibrary("firebase-crashlytics").get())
        add("implementation", libs.findLibrary("firebase-perf").get())
    }

}