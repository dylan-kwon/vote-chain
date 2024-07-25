package dylan.kwon.votechain.feature.vote.screen.add.uiMapper

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DefaultAddVoteUiMapperModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsDefaultDefaultAddVoteUiMapper(
        uiMapper: DefaultAddVoteUiMapper
    ): AddVoteUiMapper

}