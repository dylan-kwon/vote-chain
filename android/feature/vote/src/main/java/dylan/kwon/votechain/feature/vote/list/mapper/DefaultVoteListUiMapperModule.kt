package dylan.kwon.votechain.feature.vote.list.mapper

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DefaultVoteListUiMapperModule : VoteListUiMapper {

    @Binds
    abstract fun bindsVoteListUiMapper(
        uiMapper: DefaultVoteListUiMapper
    ): VoteListUiMapper

}