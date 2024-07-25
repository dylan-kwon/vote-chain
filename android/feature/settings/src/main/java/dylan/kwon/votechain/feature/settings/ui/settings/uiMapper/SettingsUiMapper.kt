package dylan.kwon.votechain.feature.settings.ui.settings.uiMapper

import dylan.kwon.votechain.core.domain.cryptoWallet.entity.DomainCryptoWallet
import dylan.kwon.votechain.feature.settings.ui.settings.SettingsUiState

interface SettingsUiMapper {

    fun combineState(
        cryptoWallet: DomainCryptoWallet?,
        isVerified: Boolean
    ): SettingsUiState


    fun DomainCryptoWallet.toUiState(
        hasPrivateKey: Boolean
    ): SettingsUiState.Loaded.CryptoWallet

}