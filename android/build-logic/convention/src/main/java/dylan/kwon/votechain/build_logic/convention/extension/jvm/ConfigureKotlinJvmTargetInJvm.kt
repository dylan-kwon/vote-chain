package dylan.kwon.votechain.build_logic.convention.extension.jvm

import dylan.kwon.votechain.build_logic.convention.common.Config
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

internal fun ExtensionContainer.configureKotlinJvmTargetInJvm() {
    configure<KotlinJvmProjectExtension> {
        compilerOptions {
            jvmTarget.set(Config.Kotlin.JVM_TARGET)
        }
    }
}