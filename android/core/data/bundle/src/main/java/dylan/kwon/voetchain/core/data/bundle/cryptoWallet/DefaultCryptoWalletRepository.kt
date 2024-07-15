package dylan.kwon.voetchain.core.data.bundle.cryptoWallet

import dylan.kwon.votechain.core.coroutine.jvm.dispatcher.DispatcherProvider
import dylan.kwon.votechain.core.data.web3j.bip39.Bip39
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.Mnemonic
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultCryptoWalletRepository @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val bip39: Bip39

) : CryptoWalletRepository {

    companion object {
        const val PASSWORD = "no-password"
    }

    override suspend fun createMnemonic(): Mnemonic {
        return withContext(dispatcherProvider.io) {
            Mnemonic(
                words = bip39.create(PASSWORD).mnemonic
            )
        }
    }
}