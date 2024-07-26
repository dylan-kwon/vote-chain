package dylan.kwon.votechain.build_logic.convention.extension.android

import dylan.kwon.votechain.build_logic.convention.extension.jvm.addJvmTestDependencies
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.addAndroidCommonDependencies(libs: VersionCatalog) {
    add("coreLibraryDesugaring", libs.findLibrary("android-desugarJdkLibs").get())

    addAndroidDependencies(libs)
    addAndroidTestDependencies(libs)
}

internal fun DependencyHandler.addAndroidDependencies(libs: VersionCatalog) {
    add("implementation", libs.findLibrary("androidx-core-ktx").get())
    add("implementation", libs.findLibrary("androidx-appcompat").get())
}


internal fun DependencyHandler.addAndroidTestDependencies(libs: VersionCatalog) {
    addJvmTestDependencies("testImplementation", libs)
    addJvmTestDependencies("androidTestImplementation", libs)

    add("testImplementation", libs.findLibrary("robolectric").get())
    add("testImplementation", libs.findLibrary("androidx-espresso-core").get())
    add("androidTestImplementation", libs.findLibrary("androidx-junit-ktx").get())
    add("androidTestImplementation", libs.findLibrary("androidx-test-runner").get())
}