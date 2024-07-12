package dylan.kwon.votechain.build_logic.convention.extension.android

import dylan.kwon.votechain.build_logic.convention.extension.jvm.addJvmTestDependencies
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.addAndroidCommonDependencies(libs: VersionCatalog) {
    add("coreLibraryDesugaring", libs.findLibrary("android-desugarJdkLibs").get())

    addAndroidXDependencies(libs)
    addAndroidTestLifecycleDependencies(libs)
}

internal fun DependencyHandler.addAndroidXDependencies(libs: VersionCatalog) {
    add("implementation", libs.findLibrary("androidx-core-ktx").get())
    add("implementation", libs.findLibrary("androidx-appcompat").get())

    addAndroidLifecycleDependencies(libs)
}

internal fun DependencyHandler.addAndroidLifecycleDependencies(libs: VersionCatalog) {
    add("implementation", libs.findLibrary("androidx-lifecycle-lifecycle-compose").get())
    add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
    add("implementation", libs.findLibrary("androidx-lifecycle-viewmodel-savedstate").get())
    add("testImplementation", libs.findLibrary("androidx-lifecycle-runtime-testing").get())
}

internal fun DependencyHandler.addAndroidTestLifecycleDependencies(libs: VersionCatalog) {
    addJvmTestDependencies(libs)
    add("testImplementation", libs.findLibrary("androidx-espresso-core").get())
    add("androidTestImplementation", libs.findLibrary("androidx-junit-ktx").get())
}