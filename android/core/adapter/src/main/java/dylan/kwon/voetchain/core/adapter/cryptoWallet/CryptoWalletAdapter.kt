package dylan.kwon.voetchain.core.adapter.cryptoWallet

import dylan.kwon.voetchain.core.adapter.cryptoWallet.mapper.toCryptoWallet
import dylan.kwon.voetchain.core.adapter.cryptoWallet.mapper.toMnemonic
import dylan.kwon.votechain.core.coroutine.jvm.dispatcher.DispatcherProvider
import dylan.kwon.votechain.core.driver.datastore.cryptoWallet.CryptoWalletDataStore
import dylan.kwon.votechain.core.driver.web3j.bip39.Bip39
import dylan.kwon.votechain.core.driver.web3j.bip39.model.Bip39CryptoWallet
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.CryptoWallet
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.Mnemonic
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletPort
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CryptoWalletAdapter @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val bip39: Bip39,
    private val dataStore: CryptoWalletDataStore
) : CryptoWalletPort {

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
            val credential = bip39.loadCredentials(
                PASSWORD,
                Bip39CryptoWallet(mnemonic.words)
            )
            credential.toCryptoWallet()
        }
    }

    override suspend fun saveCryptoWallet(cryptoWallet: CryptoWallet) {
        withContext(dispatcherProvider.io) {
            dataStore.update(
                publicKey = cryptoWallet.publicKey,
                privateKey = cryptoWallet.privateKey,
                address = cryptoWallet.address
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
                address = dataStore.address.first()
            )
        }
    }
}