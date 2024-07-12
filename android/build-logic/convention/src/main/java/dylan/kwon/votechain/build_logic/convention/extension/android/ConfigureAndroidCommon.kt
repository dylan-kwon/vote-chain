package dylan.kwon.votechain.build_logic.convention.extension.android

import com.android.build.api.dsl.CommonExtension
import dylan.kwon.votechain.build_logic.convention.common.BuildType
import dylan.kwon.votechain.build_logic.convention.common.Config
import dylan.kwon.votechain.build_logic.convention.common.FlavorDimension
import dylan.kwon.votechain.build_logic.convention.common.KeyStore
import dylan.kwon.votechain.build_logic.convention.common.ProductFlavor
import dylan.kwon.votechain.build_logic.convention.common.buildName
import dylan.kwon.votechain.build_logic.convention.extension.getOrCreate
import org.jetbrains.kotlin.konan.properties.loadProperties
import java.io.File

internal fun CommonExtension<*, *, *, *, *, *>.configureAndroidCommon(projectRootDir: File) {
    compileSdk = Config.Android.COMPILE_SDK

    defaultConfig {
        minSdk = Config.Android.MIN_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    compileOptions {
        sourceCompatibility = Config.Jvm.JAVA_VERSION
        targetCompatibility = Config.Jvm.JAVA_VERSION
        isCoreLibraryDesugaringEnabled = true
    }

    signingConfigs {
        KeyStore.values().forEach { keyStore ->
            getOrCreate(keyStore.buildName) {
                val properties = loadProperties(
                    File(projectRootDir, keyStore.propertyPath).toString()
                )
                storeFile = File(properties.getProperty("storeFile"))
                storePassword = properties.getProperty("storePassword")
                keyAlias = properties.getProperty("keyAlias")
                keyPassword = properties.getProperty("keyPassword")
            }
        }
    }

    productFlavors {
        flavorDimensions += FlavorDimension.values().map {
            it.buildName
        }
        ProductFlavor.values().forEach { flavor ->
            create(flavor.buildName) {
                dimension = flavor.dimension.buildName
            }
        }
    }

    buildTypes {
        BuildType.values().forEach { buildType ->
            getByName(buildType.buildName) {
                isMinifyEnabled = buildType.isMinifyEnabled
            }
        }

        getByName(BuildType.Release.buildName) {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}