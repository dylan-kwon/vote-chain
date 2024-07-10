package dylan.kwon.votechain.build_logic.convention.common

enum class KeyStore(
    val propertyPath: String
) {
    Release(
        propertyPath = "./keystore/release/release-keystore.properties"
    ),
    Debug(
        propertyPath = "./keystore/debug/debug-keystore.properties"
    )
}