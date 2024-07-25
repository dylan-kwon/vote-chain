package dylan.kwon.votechain.feature.settings.ui.settings.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dylan.kwon.votechain.feature.settings.ui.settings.SettingsUiState

class SettingsUiStatePreviewParameterProvider : PreviewParameterProvider<SettingsUiState> {

    private val loading = SettingsUiState.Loading

    private val loadedAndEmptyPrivateKey = SettingsUiState.Loaded(
        cryptoWallet = SettingsUiState.Loaded.CryptoWallet(
            address = "0x0000000000000000",
        )
    )

    private val loadedAndHasPrivateKey = loadedAndEmptyPrivateKey.copy(
        cryptoWallet = loadedAndEmptyPrivateKey.cryptoWallet.copy(
            privateKey = "0x00000000000000000000000000000"
        )
    )

    override val values: Sequence<SettingsUiState> = sequenceOf(
        loading, loadedAndHasPrivateKey, loadedAndEmptyPrivateKey
    )

}