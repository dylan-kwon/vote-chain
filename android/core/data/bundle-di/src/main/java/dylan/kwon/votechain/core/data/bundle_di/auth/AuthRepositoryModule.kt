package dylan.kwon.votechain.core.data.bundle_di.auth

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dylan.kwon.voetchain.core.data.bundle.auth.DefaultAuthRepository
import dylan.kwon.votechain.core.domain.auth.port.AuthRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsDefaultAuthRepository(
        authRepository: DefaultAuthRepository
    ): AuthRepository
}