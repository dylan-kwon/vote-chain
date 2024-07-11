package dylan.kwon.votechain.build_logic.convention.extension.jvm

import dylan.kwon.votechain.build_logic.convention.common.Config
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure

internal fun ExtensionContainer.configureJavaVersionInJvm() {
    configure<JavaPluginExtension> {
        sourceCompatibility = Config.Jvm.JAVA_VERSION
        targetCompatibility = Config.Jvm.JAVA_VERSION
    }
}
