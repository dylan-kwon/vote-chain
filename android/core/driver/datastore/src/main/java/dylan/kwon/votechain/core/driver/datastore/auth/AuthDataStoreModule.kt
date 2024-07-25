package dylan.kwon.votechain.core.driver.datastore.auth

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthDataStoreModule {

    @Binds
    @Singleton
    abstract fun bindsDefaultAuthDataStore(
        authDataStore: DefaultAuthDataStore
    ): AuthDataStore

}