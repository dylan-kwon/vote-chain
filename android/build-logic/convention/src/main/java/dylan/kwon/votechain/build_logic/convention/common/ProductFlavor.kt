package dylan.kwon.votechain.build_logic.convention.common

enum class ProductFlavor(
    val dimension: FlavorDimension,
    val useSuffix: Boolean = true,
) {
    Prod(
        dimension = FlavorDimension.Type,
        useSuffix = false,
    ),
    Dev(
        dimension = FlavorDimension.Type,
    )
}

val ProductFlavor.buildName
    get() = name.lowercase()

val ProductFlavor.versionNameSuffix
    get() = "-${buildName}"

val ProductFlavor.applicationIdSuffix
    get() = ".${buildName}"