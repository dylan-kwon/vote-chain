package dylan.kwon.votechain.build_logic.convention.extension.jvm

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addJvmTestDependencies(libs: VersionCatalog) {
    add("testImplementation", libs.findLibrary("junit").get())
    add("testImplementation", libs.findLibrary("truth").get())
    add("testImplementation", libs.findLibrary("mockk").get())
    add("testImplementation", libs.findLibrary("robolectric").get())
}