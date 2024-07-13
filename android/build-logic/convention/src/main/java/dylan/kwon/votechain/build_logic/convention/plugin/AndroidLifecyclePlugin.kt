package dylan.kwon.votechain.build_logic.convention.plugin

import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.artifacts.dsl.DependencyHandler

class AndroidLifecyclePlugin : ProjectPlugin() {

    override fun DependencyHandler.onDependencies() {
        add("implementation", libs.findLibrary("androidx-lifecycle-lifecycle-compose").get())
        add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
        add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-savedstate").get())
        add("testImplementation", libs.findLibrary("androidx-lifecycle-runtime-testing").get())
    }
}