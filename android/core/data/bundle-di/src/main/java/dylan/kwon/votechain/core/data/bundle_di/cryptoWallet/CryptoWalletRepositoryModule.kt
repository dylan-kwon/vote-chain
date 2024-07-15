package dylan.kwon.votechain.core.data.bundle_di.cryptoWallet

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dylan.kwon.voetchain.core.data.bundle.cryptoWallet.DefaultCryptoWalletRepository
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CryptoWalletRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsCryptoWalletRepository(
        cryptoWalletRepository: DefaultCryptoWalletRepository
    ): CryptoWalletRepository
}