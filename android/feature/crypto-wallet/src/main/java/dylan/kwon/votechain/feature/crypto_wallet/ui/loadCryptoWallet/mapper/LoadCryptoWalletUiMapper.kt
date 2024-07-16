package dylan.kwon.votechain.feature.crypto_wallet.ui.loadCryptoWallet.mapper

import dylan.kwon.votechain.core.domain.cryptoWallet.entity.Mnemonic

interface LoadCryptoWalletUiMapper {

    fun String.toMnemonic(): Mnemonic

}