package dylan.kwon.voetchain.core.adapter.vote

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dylan.kwon.votechain.core.domain.vote.port.VotePort
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class VoteAdapterModule {
    @Binds
    @Singleton
    abstract fun bindsVoteAdapter(
        voteAdapter: VoteAdapter
    ): VotePort
}