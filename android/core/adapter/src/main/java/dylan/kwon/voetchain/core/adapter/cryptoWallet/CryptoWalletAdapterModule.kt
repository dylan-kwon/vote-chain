package dylan.kwon.voetchain.core.adapter.cryptoWallet

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletPort
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CryptoWalletAdapterModule {
    @Binds
    @Singleton
    abstract fun bindsCryptoWalletAdapter(
        cryptoWalletAdapter: CryptoWalletAdapter
    ): CryptoWalletPort
}