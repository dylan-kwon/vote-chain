@file:OptIn(ExperimentalStdlibApi::class)

package dylan.kwon.voetchain.core.data.bundle.cryptoWallet.mapper

import dylan.kwon.votechain.core.domain.cryptoWallet.entity.CryptoWallet
import dylan.kwon.votechain.core.driver.web3j.bip39.model.Credential

internal fun Credential.toCryptoWallet(): CryptoWallet = CryptoWallet(
    publicKey = public.toHexString(),
    privateKey = private.toHexString(),
    address = address
)