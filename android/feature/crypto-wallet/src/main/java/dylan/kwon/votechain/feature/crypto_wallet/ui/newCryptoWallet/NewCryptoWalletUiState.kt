package dylan.kwon.votechain.feature.crypto_wallet.ui.newCryptoWallet

import kotlinx.collections.immutable.ImmutableList

data class NewCryptoWalletUiState(
    val mnemonic: Mnemonic = Mnemonic.Loading
) {
    sealed interface Mnemonic {
        data object Loading : Mnemonic

        data class Loaded(
            val words: ImmutableList<String>
        ) : Mnemonic {
            val size: Int
                get() = words.size
        }

        data object Error : Mnemonic

        val isLoaded: Boolean
            get() = this is Loaded
    }
}
