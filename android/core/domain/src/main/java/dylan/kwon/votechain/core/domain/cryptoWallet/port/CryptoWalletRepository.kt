package dylan.kwon.votechain.core.domain.cryptoWallet.port

import dylan.kwon.votechain.core.domain.cryptoWallet.entity.Mnemonic

interface CryptoWalletRepository {

    suspend fun createMnemonic(): Mnemonic

}