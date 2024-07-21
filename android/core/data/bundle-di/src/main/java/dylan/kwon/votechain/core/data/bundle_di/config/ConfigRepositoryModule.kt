package dylan.kwon.votechain.core.data.bundle_di.config

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dylan.kwon.voetchain.core.data.bundle.config.DefaultConfigRepository
import dylan.kwon.votechain.core.domain.config.port.ConfigRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConfigRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsConfigRepository(
        repository: DefaultConfigRepository
    ): ConfigRepository
}