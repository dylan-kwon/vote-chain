package dylan.kwon.votechain.core.driver.web3j.bip39

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Bip39Module {

    @Binds
    @Singleton
    abstract fun bindsWeb3jBip39(
        bip39: Web3jBip39
    ): Bip39

}