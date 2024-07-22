package dylan.kwon.votechain.core.data.vote_contract

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DefaultVoteContractModule {

    @Binds
    @Singleton
    abstract fun bindsDefaultVoteContract(
        contract: Web3jVoteContract
    ): VoteContract

}