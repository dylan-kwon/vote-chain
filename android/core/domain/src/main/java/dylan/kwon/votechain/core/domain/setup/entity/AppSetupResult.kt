package dylan.kwon.votechain.core.domain.setup.entity

enum class AppSetupResult {
    AuthError,
    ConfigError,
    NeedCryptoWallet,
    Completed
}