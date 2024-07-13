package dylan.kwon.votechain.build_logic.convention.plugin

import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.artifacts.dsl.DependencyHandler

class KotlinImmutableCollectionsPlugin : ProjectPlugin() {

    override fun DependencyHandler.onDependencies() {
        add("implementation", libs.findLibrary("kotlinx-collections-immutable").get())
    }
}