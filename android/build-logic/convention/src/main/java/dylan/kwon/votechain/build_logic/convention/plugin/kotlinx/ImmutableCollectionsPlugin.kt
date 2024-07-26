package dylan.kwon.votechain.build_logic.convention.plugin.kotlinx

import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.artifacts.dsl.DependencyHandler

class ImmutableCollectionsPlugin : ProjectPlugin() {

    override fun DependencyHandler.onDependencies() {
        add("implementation", libs.findLibrary("kotlinx-collections-immutable").get())
    }
}