package dylan.kwon.voetchain.core.data.bundle.cryptoWallet

import dylan.kwon.voetchain.core.data.bundle.cryptoWallet.mapper.toCryptoWallet
import dylan.kwon.voetchain.core.data.bundle.cryptoWallet.mapper.toMnemonic
import dylan.kwon.votechain.core.coroutine.jvm.dispatcher.DispatcherProvider
import dylan.kwon.votechain.core.data.datastore.cryptoWallet.CryptoWalletDataStore
import dylan.kwon.votechain.core.data.web3j.bip39.Bip39
import dylan.kwon.votechain.core.data.web3j.bip39.model.Bip39CryptoWallet
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.CryptoWallet
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.Mnemonic
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultCryptoWalletRepository @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val bip39: Bip39,
    private val dataStore: CryptoWalletDataStore
) : CryptoWalletRepository {

    companion object {
        const val PASSWORD = "no-password"
    }

    override suspend fun createMnemonic(): Mnemonic {
        return withContext(dispatcherProvider.io) {
            bip39.create(PASSWORD).toMnemonic()
        }
    }

    override suspend fun loadCryptoWallet(mnemonic: Mnemonic): CryptoWallet {
        return withContext(dispatcherProvider.io) {
            val keyPair = bip39.loadKeyPair(
                PASSWORD,
                Bip39CryptoWallet(mnemonic.words)
            )
            keyPair.toCryptoWallet()
        }
    }

    override suspend fun saveCryptoWallet(cryptoWallet: CryptoWallet) {
        withContext(dispatcherProvider.io) {
            dataStore.update(
                publicKey = cryptoWallet.publicKey,
                privateKey = cryptoWallet.privateKey
            )
        }
    }

    override suspend fun hasCryptoWallet(): Boolean {
        return withContext(dispatcherProvider.io) {
            getSavedCryptoWallet().privateKey.isNotEmpty()
        }
    }

    override suspend fun getSavedCryptoWallet(): CryptoWallet {
        return withContext(dispatcherProvider.io) {
            CryptoWallet(
                publicKey = dataStore.publicKey.first(),
                privateKey = dataStore.privateKey.first(),
            )
        }
    }
}