package dylan.kwon.votechain.core.domain.cryptoWallet.entity

data class Mnemonic(
    val words: List<String>
) {
    val size: Int
        get() = words.size
}