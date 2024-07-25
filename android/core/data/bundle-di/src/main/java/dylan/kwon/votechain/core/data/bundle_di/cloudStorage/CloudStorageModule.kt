package dylan.kwon.votechain.core.data.bundle_di.cloudStorage

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dylan.kwon.voetchain.core.data.bundle.cloudStorage.DefaultCloudStorage
import dylan.kwon.votechain.core.domain.cloudStorage.CloudStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CloudStorageModule {
    @Binds
    @Singleton
    abstract fun bindsCloudStorage(
        cloudStorage: DefaultCloudStorage
    ): CloudStorage
}