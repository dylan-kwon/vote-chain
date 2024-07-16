package dylan.kwon.votechain.feature.crypto_wallet.ui.loadCryptoWallet.mapper

import dylan.kwon.votechain.core.domain.cryptoWallet.entity.Mnemonic
import javax.inject.Inject

class DefaultLoadCryptoWalletUiMapper @Inject constructor() : LoadCryptoWalletUiMapper {

    override fun String.toMnemonic(): Mnemonic {
        return Mnemonic(
            words = split(" ")
        )
    }

}