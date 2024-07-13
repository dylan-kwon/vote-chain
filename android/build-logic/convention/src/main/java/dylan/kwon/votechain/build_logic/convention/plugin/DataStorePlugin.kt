package dylan.kwon.votechain.build_logic.convention.plugin

import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.artifacts.dsl.DependencyHandler

class DataStorePlugin : ProjectPlugin() {

    override fun DependencyHandler.onDependencies() {
        if (isAndroid) {
            add("implementation", libs.findLibrary("datastore-preferences").get())
        }
        if (isJvmLibrary) {
            add("implementation", libs.findLibrary("datastore-preferences-core").get())
        }
    }
}