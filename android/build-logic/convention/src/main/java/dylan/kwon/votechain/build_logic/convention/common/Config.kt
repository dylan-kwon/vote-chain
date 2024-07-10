package dylan.kwon.votechain.build_logic.convention.common

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Config {
    object Jvm {
        val JAVA_VERSION = JavaVersion.VERSION_17
    }

    object Kotlin {
        val JVM_TARGET = JvmTarget.JVM_17
    }

    object Android {
        const val COMPILE_SDK = 34
        const val TARGET_SDK = 34
        const val MIN_SDK = 24
    }

    object Compose {
        const val COMPILER_VERSION = "1.5.1"
    }
}