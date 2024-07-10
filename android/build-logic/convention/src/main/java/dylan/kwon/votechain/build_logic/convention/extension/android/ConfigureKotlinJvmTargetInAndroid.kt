package dylan.kwon.votechain.build_logic.convention.extension.android

import dylan.kwon.votechain.build_logic.convention.common.Config
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

fun Project.configureKotlinJvmTargetInAndroid() {
    configure<KotlinAndroidProjectExtension> {
        compilerOptions {
            jvmTarget.set(Config.Kotlin.JVM_TARGET)
        }
    }
}
