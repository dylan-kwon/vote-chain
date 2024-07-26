package dylan.kwon.votechain.build_logic.convention.extension.jvm

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addJvmTestDependencies(
    configurationName: String,
    libs: VersionCatalog
) {
    add(configurationName, libs.findLibrary("junit").get())
    add(configurationName, libs.findLibrary("truth").get())
    add(configurationName, libs.findLibrary("mockk").get())
}