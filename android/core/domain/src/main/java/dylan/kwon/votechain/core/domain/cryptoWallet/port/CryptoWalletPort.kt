package dylan.kwon.votechain.core.domain.cryptoWallet.port

import dylan.kwon.votechain.core.domain.cryptoWallet.entity.CryptoWallet
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.Mnemonic

interface CryptoWalletPort {

    suspend fun createMnemonic(): Mnemonic

    suspend fun loadCryptoWallet(mnemonic: Mnemonic): CryptoWallet

    suspend fun saveCryptoWallet(cryptoWallet: CryptoWallet)

    suspend fun hasCryptoWallet(): Boolean

    suspend fun getSavedCryptoWallet(): CryptoWallet

}