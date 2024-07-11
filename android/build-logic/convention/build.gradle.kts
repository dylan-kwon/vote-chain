import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "dylan.kwon.votechain.build_logic.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    val pluginPackage = "${group}.plugin"
    plugins {
        register("jvmLibrary") {
            id = "votechain.jvm.library"
            implementationClass = "${pluginPackage}.JvmLibraryPlugin"
        }
        register("androidLibrary") {
            id = "votechain.android.library"
            implementationClass = "${pluginPackage}.AndroidLibraryPlugin"
        }
        register("androidApplication") {
            id = "votechain.android.application"
            implementationClass = "${pluginPackage}.AndroidApplicationPlugin"
        }
        register("compose") {
            id = "votechain.compose"
            implementationClass = "${pluginPackage}.ComposePlugin"
        }
        register("hilt") {
            id = "votechain.hilt"
            implementationClass = "${pluginPackage}.HiltPlugin"
        }
    }
}