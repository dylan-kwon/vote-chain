package dylan.kwon.votechain.build_logic.convention.plugin

import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.artifacts.dsl.DependencyHandler

class CoroutinePlugin : ProjectPlugin() {

    override fun DependencyHandler.onDependencies() {
        when {
            isAndroid -> {
                add("implementation", libs.findLibrary("kotlinx-coroutines-android").get())
            }

            isJvmLibrary -> {
                add("implementation", libs.findLibrary("kotlinx-coroutines-core").get())
            }

            else -> throw Exception()
        }
        add("testImplementation", libs.findLibrary("kotlinx-coroutines-test").get())
    }
}