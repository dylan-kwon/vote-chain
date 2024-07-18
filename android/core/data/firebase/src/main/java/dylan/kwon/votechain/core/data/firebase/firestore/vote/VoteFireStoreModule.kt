package dylan.kwon.votechain.core.data.firebase.firestore.vote

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class VoteFireStoreModule {
    @Binds
    abstract fun bindsDefaultVoteFireStore(
        voteFireStore: DefaultVoteFireStore
    ): VoteFireStore
}