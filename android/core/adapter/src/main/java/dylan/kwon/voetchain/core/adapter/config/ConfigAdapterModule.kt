package dylan.kwon.voetchain.core.adapter.config

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dylan.kwon.votechain.core.domain.config.port.ConfigPort
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ConfigAdapterModule {
    @Binds
    @Singleton
    abstract fun bindsConfigAdapter(
        configAdapter: ConfigAdapter
    ): ConfigPort
}