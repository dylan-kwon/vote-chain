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
    compileOnly(libs.compose.gradlePlugin)
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
        register("androidLifecycle") {
            id = "votechain.android.lifecycle"
            implementationClass = "${pluginPackage}.AndroidLifecyclePlugin"
        }
        register("compose") {
            id = "votechain.compose"
            implementationClass = "${pluginPackage}.ComposePlugin"
        }
        register("hilt") {
            id = "votechain.hilt"
            implementationClass = "${pluginPackage}.HiltPlugin"
        }
        register("coroutine") {
            id = "votechain.coroutine"
            implementationClass = "${pluginPackage}.CoroutinePlugin"
        }
        register("serialization") {
            id = "votechain.serialization"
            implementationClass = "${pluginPackage}.KotlinSerializationPlugin"
        }
        register("immutableCollections") {
            id = "votechain.immutableCollections"
            implementationClass = "${pluginPackage}.KotlinImmutableCollectionsPlugin"
        }
        register("dataStore") {
            id = "votechain.datastore"
            implementationClass = "${pluginPackage}.DataStorePlugin"
        }
        register("parcelize") {
            id = "votechain.android.parcelize"
            implementationClass = "${pluginPackage}.ParcelizePlugin"
        }
    }
}