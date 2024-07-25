package dylan.kwon.votechain.feature.settings.ui.settings

sealed interface SettingsUiState {

    data object Loading : SettingsUiState

    data class Loaded(
        val cryptoWallet: CryptoWallet,
    ) : SettingsUiState {

        data class CryptoWallet(
            val address: String = "",
            val privateKey: String? = null
        )

        val isPrivateKeyVisible: Boolean
            get() = cryptoWallet.privateKey != null

        val isPrivateKeyShowButtonVisible: Boolean
            get() = !isPrivateKeyVisible
    }

}