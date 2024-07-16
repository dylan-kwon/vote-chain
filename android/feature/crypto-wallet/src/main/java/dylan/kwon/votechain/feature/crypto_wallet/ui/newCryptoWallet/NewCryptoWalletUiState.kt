package dylan.kwon.votechain.feature.crypto_wallet.ui.newCryptoWallet

import kotlinx.collections.immutable.ImmutableList

data class NewCryptoWalletUiState(
    val mnemonic: Mnemonic = Mnemonic.Loading,
    val cryptoWalletState: CryptoWalletState = CryptoWalletState.Default,
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

    enum class CryptoWalletState {
        Default,
        Saving,
        Saved;

        val isSaving: Boolean
            get() = this == Saving

        val isSaved: Boolean
            get() = this == Saving
    }

    val isShowingMnemonicMenus: Boolean
        get() = !cryptoWalletState.isSaving && mnemonic.isLoaded
}
