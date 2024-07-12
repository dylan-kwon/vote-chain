package dylan.kwon.votechain.build_logic.convention.plugin

import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

class CoroutinePlugin : ProjectPlugin() {

    override fun DependencyHandler.onDependencies() {
        when {
            isAndroid -> {
                add("implementation", libs.findLibrary("kotlinx-coroutines-android").get())
                add("implementation", project(":core:coroutine:android"))
            }

            isJvmLibrary -> {
                add("implementation", libs.findLibrary("kotlinx-coroutines-core").get())
            }

            else -> throw Exception()
        }
        add("testImplementation", libs.findLibrary("kotlinx-coroutines-test").get())
    }
}