package dylan.kwon.voetchain.core.data.bundle.cryptoWallet.mapper

import dylan.kwon.votechain.core.driver.web3j.bip39.model.Bip39CryptoWallet
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.Mnemonic

fun Bip39CryptoWallet.toMnemonic(): Mnemonic = Mnemonic(
    words = mnemonic
)