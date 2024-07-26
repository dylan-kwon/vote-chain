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
            implementationClass = "${pluginPackage}.jvm.JvmLibraryPlugin"
        }
        register("androidLibrary") {
            id = "votechain.android.library"
            implementationClass = "${pluginPackage}.android.AndroidLibraryPlugin"
        }
        register("androidApplication") {
            id = "votechain.android.application"
            implementationClass = "${pluginPackage}.android.AndroidApplicationPlugin"
        }
        register("androidLifecycle") {
            id = "votechain.android.lifecycle"
            implementationClass = "${pluginPackage}.android.AndroidLifecyclePlugin"
        }
        register("compose") {
            id = "votechain.android.compose"
            implementationClass = "${pluginPackage}.android.AndroidComposePlugin"
        }
        register("hilt") {
            id = "votechain.hilt"
            implementationClass = "${pluginPackage}.hilt.HiltPlugin"
        }
        register("coroutine") {
            id = "votechain.kotlinx.coroutine"
            implementationClass = "${pluginPackage}.kotlinx.CoroutinePlugin"
        }
        register("serialization") {
            id = "votechain.kotlinx.serialization"
            implementationClass = "${pluginPackage}.kotlinx.SerializationPlugin"
        }
        register("immutableCollections") {
            id = "votechain.kotlinx.immutableCollections"
            implementationClass = "${pluginPackage}.kotlinx.ImmutableCollectionsPlugin"
        }
        register("dataStore") {
            id = "votechain.android.datastore"
            implementationClass = "${pluginPackage}.android.AndroidDataStorePlugin"
        }
        register("parcelize") {
            id = "votechain.android.parcelize"
            implementationClass = "${pluginPackage}.android.AndroidParcelizePlugin"
        }
        register("androidFeature") {
            implementationClass = "${pluginPackage}.android.AndroidFeaturePlugin"
            id = "votechain.android.feature"
        }
        register("firebase") {
            id = "votechain.firebase"
            implementationClass = "${pluginPackage}.firebase.FirebasePlugin"
        }
    }
}