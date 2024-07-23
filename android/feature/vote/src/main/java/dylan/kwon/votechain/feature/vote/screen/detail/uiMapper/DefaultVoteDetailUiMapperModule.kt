package dylan.kwon.votechain.feature.vote.screen.detail.uiMapper

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DefaultVoteDetailUiMapperModule {

    @Binds
    @ViewModelScoped
    abstract fun bindVoteDetailUiMapper(
        uiMapper: DefaultVoteDetailUiMapper
    ): VoteDetailUiMapper


}