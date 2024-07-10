package dylan.kwon.votechain.build_logic.convention.common

enum class BuildType(
    val isMinifyEnabled: Boolean,
    val isShrinkResources: Boolean,
    val keyStore: KeyStore,
    val useSuffix: Boolean = true,
) {
    Release(
        isMinifyEnabled = true,
        isShrinkResources = true,
        keyStore = KeyStore.Release,
        useSuffix = false
    ),

    Debug(
        isMinifyEnabled = false,
        isShrinkResources = false,
        keyStore = KeyStore.Debug
    )
}

val BuildType.buildName
    get() = name.lowercase()


val BuildType.versionNameSuffix
    get() = "-$buildName"

val BuildType.applicationIdSuffix
    get() = ".$buildName"
