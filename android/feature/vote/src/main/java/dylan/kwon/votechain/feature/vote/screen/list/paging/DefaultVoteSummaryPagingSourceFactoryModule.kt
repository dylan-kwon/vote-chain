package dylan.kwon.votechain.feature.vote.screen.list.paging

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DefaultVoteSummaryPagingSourceFactoryModule {

    @Binds
    abstract fun bindsDefaultVoteSummaryPagingSourceFactory(
        factory: DefaultVoteSummaryPagingSourceFactory
    ): VoteSummaryPagingSourceFactory

}