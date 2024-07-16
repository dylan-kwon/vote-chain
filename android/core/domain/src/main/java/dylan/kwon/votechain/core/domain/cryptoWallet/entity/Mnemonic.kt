package dylan.kwon.votechain.core.domain.cryptoWallet.entity

data class Mnemonic(
    val words: List<String>
) {

    companion object {
        const val MINIMUM_WORD_SIZE = 12
    }

    val size: Int
        get() = words.size

    internal val isValid: Boolean
        get() = size >= MINIMUM_WORD_SIZE

}