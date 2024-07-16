package dylan.kwon.votechain.feature.crypto_wallet.ui.loadCryptoWallet.mapper

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class LoadCryptoWalletUiMapperModule {

    @Binds
    @ViewModelScoped
    abstract fun binds(
        uiMapper: DefaultLoadCryptoWalletUiMapper
    ): LoadCryptoWalletUiMapper

}