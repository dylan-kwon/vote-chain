package dylan.kwon.votechain.build_logic.convention.extension.android

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.kotlin.dsl.assign
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

internal fun CommonExtension<*, *, *, *, *, *>.configureCompose() {
    buildFeatures {
        compose = true
    }
}

internal fun ExtensionContainer.configureComposeCompiler() {
    configure<ComposeCompilerGradlePluginExtension> {
        enableStrongSkippingMode = true
    }
}