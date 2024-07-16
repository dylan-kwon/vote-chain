package dylan.kwon.votechain.feature.crypto_wallet.ui.loadCryptoWallet

data class LoadCryptoWalletUiState(
    val mnemonic: String = "",
    val mnemonicState: MnemonicState = MnemonicState.Default,
    val cryptoWalletState: CryptoWalletState = CryptoWalletState.Default
) {
    enum class MnemonicState {
        Default,
        Valid,
        Invalid;

        val isValid: Boolean
            get() = this == Valid

        val isInvalid: Boolean
            get() = this == Invalid
    }

    enum class CryptoWalletState {
        Default,
        Loading,
        Loaded;

        val isLoading: Boolean
            get() = this == Loading

        val isLoaded: Boolean
            get() = this == Loaded
    }
}
