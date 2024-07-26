package dylan.kwon.votechain.build_logic.convention.plugin.android

import dylan.kwon.votechain.build_logic.convention.plugin.base.ProjectPlugin
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.PluginManager
import org.gradle.kotlin.dsl.project

class AndroidFeaturePlugin : ProjectPlugin() {

    override fun PluginManager.onPlugin() {
        apply("votechain.android.library")
        apply("votechain.android.parcelize")
        apply("votechain.android.lifecycle")
        apply("votechain.android.compose")
        apply("votechain.kotlinx.coroutine")
        apply("votechain.kotlinx.serialization")
        apply("votechain.kotlinx.immutableCollections")
        apply("votechain.hilt")
    }

    override fun DependencyHandler.onDependencies() {
        add("implementation", project(":core:domain"))
        add("implementation", project(":core:architecture:mvi"))
        add("implementation", project(":core:ui:navigation"))
        add("implementation", project(":core:ui:compose-ext"))
        add("implementation", project(":core:ui:design-system"))
        add("implementation", project(":core:coroutine:jvm"))
        add("testImplementation", project(":core:coroutine:test"))

        add("implementation", libs.findLibrary("timeago").get())
    }
}