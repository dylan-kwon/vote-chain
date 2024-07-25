package dylan.kwon.votechain.core.domain.cryptoWallet.entity

typealias DomainCryptoWallet = CryptoWallet

class CryptoWallet(
    val publicKey: String,
    val privateKey: String,
    val address: String,
)