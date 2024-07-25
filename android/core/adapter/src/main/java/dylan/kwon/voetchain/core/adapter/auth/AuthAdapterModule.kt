package dylan.kwon.voetchain.core.adapter.auth

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dylan.kwon.votechain.core.domain.auth.port.AuthPort
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthAdapterModule {
    @Binds
    @Singleton
    abstract fun bindsDefaultAuthAdapter(
        adapter: AuthAdapter
    ): AuthPort
}