package dylan.kwon.votechain.build_logic.convention.extension.android

import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addJvmDesugaringDependency(libs: VersionCatalog) {
    add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
}