package dylan.kwon.votechain.core.domain.cryptoWallet.port

import dylan.kwon.votechain.core.domain.cryptoWallet.entity.CryptoWallet
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.Mnemonic

interface CryptoWalletRepository {

    suspend fun createMnemonic(): Mnemonic

    suspend fun loadCryptoWallet(mnemonic: Mnemonic): CryptoWallet

    suspend fun saveCryptoWallet(cryptoWallet: CryptoWallet)

    suspend fun hasCryptoWallet(): Boolean

}