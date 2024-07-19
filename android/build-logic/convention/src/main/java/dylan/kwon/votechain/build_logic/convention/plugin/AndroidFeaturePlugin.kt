package dylan.kwon.votechain.build_logic.convention.plugin

import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.project

class AndroidFeaturePlugin : ProjectPlugin() {

    override fun PluginManager.onPlugin() {
        apply("votechain.android.library")
        apply("votechain.android.parcelize")
        apply("votechain.android.lifecycle")
        apply("votechain.coroutine")
        apply("votechain.serialization")
        apply("votechain.immutableCollections")
        apply("votechain.hilt")
        apply("votechain.compose")
    }

    override fun DependencyHandler.onDependencies() {
        add("implementation", project(":core:domain"))
        add("implementation", project(":core:architecture:mvi"))
        add("implementation", project(":core:ui:compose-ext"))
        add("implementation", project(":core:ui:design-system"))
        add("implementation", project(":core:coroutine:jvm"))
        add("testImplementation", project(":core:coroutine:test"))
    }
}