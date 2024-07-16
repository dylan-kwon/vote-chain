package dylan.kwon.votechain.core.data.datastore.cryptoWallet

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CryptoWalletDataStoreModule {

    @Binds
    @Singleton
    abstract fun bindsDefaultAuthDataStore(
        cryptoWalletDataStore: DefaultCryptoWalletDataStore
    ): CryptoWalletDataStore

}