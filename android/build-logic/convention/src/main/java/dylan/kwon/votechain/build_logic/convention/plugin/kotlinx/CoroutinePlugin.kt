package dylan.kwon.votechain.build_logic.convention.plugin.kotlinx

import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.artifacts.dsl.DependencyHandler

class CoroutinePlugin : ProjectPlugin() {

    override fun DependencyHandler.onDependencies() {
        when {
            isAndroid -> {
                add("implementation", libs.findLibrary("kotlinx-coroutines-android").get())
            }

            else -> add("implementation", libs.findLibrary("kotlinx-coroutines-core").get())
        }
        add("testImplementation", libs.findLibrary("kotlinx-coroutines-test").get())
    }

}