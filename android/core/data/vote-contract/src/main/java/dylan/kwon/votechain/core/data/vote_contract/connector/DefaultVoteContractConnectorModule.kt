package dylan.kwon.votechain.core.data.vote_contract.connector

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DefaultVoteContractConnectorModule {

    @Binds
    @Singleton
    abstract fun bindsDefaultVoteContractConnector(
        connector: Web3jVoteContractConnector
    ): VoteContractConnector

}