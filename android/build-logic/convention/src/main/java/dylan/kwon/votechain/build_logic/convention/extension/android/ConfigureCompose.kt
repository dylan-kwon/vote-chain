package dylan.kwon.votechain.build_logic.convention.extension.android

import com.android.build.api.dsl.CommonExtension
import dylan.kwon.votechain.build_logic.convention.common.Config

internal fun CommonExtension<*, *, *, *, *, *>.configureCompose() {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Config.Compose.COMPILER_VERSION
    }
}