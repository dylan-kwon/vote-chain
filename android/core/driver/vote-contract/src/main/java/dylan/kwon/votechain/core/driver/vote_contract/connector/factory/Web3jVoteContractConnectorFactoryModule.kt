package dylan.kwon.votechain.core.driver.vote_contract.connector.factory

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class Web3jVoteContractConnectorFactoryModule {
    @Binds
    @Singleton
    abstract fun bindsWeb3jVoteContractConnectorFactory(
        factory: Web3jVoteContractConnectorFactory
    ): VoteContractConnectorFactory
}