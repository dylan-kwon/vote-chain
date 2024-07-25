package dylan.kwon.votechain.feature.settings.ui.settings.uiMapper

import dagger.hilt.android.scopes.ViewModelScoped
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.DomainCryptoWallet
import dylan.kwon.votechain.feature.settings.ui.settings.SettingsUiState
import javax.inject.Inject
import dylan.kwon.votechain.feature.settings.ui.settings.SettingsUiState.Loaded.CryptoWallet as CryptoWalletUiState

@ViewModelScoped
class DefaultSettingsUiMapper @Inject constructor() : SettingsUiMapper {

    override fun combineState(
        cryptoWallet: DomainCryptoWallet?,
        isVerified: Boolean
    ): SettingsUiState {
        if (cryptoWallet == null) {
            return SettingsUiState.Loading
        }
        return SettingsUiState.Loaded(
            cryptoWallet = cryptoWallet.toUiState(
                hasPrivateKey = isVerified
            )
        )
    }

    override fun DomainCryptoWallet.toUiState(hasPrivateKey: Boolean): CryptoWalletUiState =
        CryptoWalletUiState(
            address = address,
            privateKey = when (hasPrivateKey) {
                true -> privateKey
                else -> null
            }
        )
}