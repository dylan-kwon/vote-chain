package dylan.kwon.votechain.build_logic.convention.common

enum class FlavorDimension {
    Type
}

val FlavorDimension.buildName
    get() = name.lowercase()