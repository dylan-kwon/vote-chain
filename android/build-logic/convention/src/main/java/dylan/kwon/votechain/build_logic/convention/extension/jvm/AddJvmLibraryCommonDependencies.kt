package dylan.kwon.votechain.build_logic.convention.extension.jvm

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.addJvmLibraryCommonDependencies(libs: VersionCatalog) {
    addJvmTestDependencies(libs)
}
