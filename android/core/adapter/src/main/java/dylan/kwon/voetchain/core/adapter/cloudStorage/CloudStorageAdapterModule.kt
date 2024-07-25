package dylan.kwon.voetchain.core.adapter.cloudStorage

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dylan.kwon.votechain.core.domain.cloudStorage.CloudStoragePort
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CloudStorageAdapterModule {
    @Binds
    @Singleton
    abstract fun bindsCloudStorageAdapter(
        adapter: CloudStorageAdapter
    ): CloudStoragePort
}