package dylan.kwon.votechain.core.driver.firebase.config

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProdRemoteConfigStoreModule {

    @Binds
    @Singleton
    abstract fun bindsDevRemoteConfigStore(
        remoteConfigStore: ProdRemoteConfigStore
    ): RemoteConfigStore

}