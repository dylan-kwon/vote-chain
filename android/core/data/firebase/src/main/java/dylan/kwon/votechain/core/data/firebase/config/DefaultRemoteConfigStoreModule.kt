package dylan.kwon.votechain.core.data.firebase.config

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DefaultRemoteConfigStoreModule {

    @Binds
    @Singleton
    abstract fun bindsDefaultRemoteConfigStore(
        remoteConfigStore: DefaultRemoteConfigStore
    ): RemoteConfigStore

}