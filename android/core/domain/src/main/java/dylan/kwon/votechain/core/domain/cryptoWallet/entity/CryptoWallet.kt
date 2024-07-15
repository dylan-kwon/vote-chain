package dylan.kwon.votechain.core.domain.cryptoWallet.entity

class CryptoWallet(
    private val publicKey: ByteArray,
    private val privateKey: ByteArray,
)