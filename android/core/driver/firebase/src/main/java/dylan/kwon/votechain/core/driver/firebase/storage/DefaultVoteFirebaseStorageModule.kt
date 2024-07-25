package dylan.kwon.votechain.core.driver.firebase.storage

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DefaultVoteFirebaseStorageModule {

    @Binds
    @Singleton
    abstract fun bindsDefaultFirebaseStorage(
        voteFirebaseStorage: DefaultVoteFirebaseStorage
    ): VoteFirebaseStorage

}