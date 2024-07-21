package dylan.kwon.votechain.core.data.bundle_di.vote

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dylan.kwon.voetchain.core.data.bundle.vote.DefaultVoteRepository
import dylan.kwon.votechain.core.domain.vote.port.VoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class VoteRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsVoteRepository(
        voteRepository: DefaultVoteRepository
    ): VoteRepository
}