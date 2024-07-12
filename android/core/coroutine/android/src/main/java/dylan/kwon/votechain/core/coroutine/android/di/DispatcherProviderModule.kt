package dylan.kwon.votechain.core.coroutine.android.di//package dylan.kwon.auth.ui.screen.simplePassword

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dylan.kwon.votechain.core.coroutine.android.dispatcher.DefaultDispatcherProvider
import dylan.kwon.votechain.core.coroutine.android.dispatcher.DispatcherProvider
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherProviderModule {
    @Binds
    @Singleton
    abstract fun bindsDefaultDispatcherProvider(
        dispatcherProvider: DefaultDispatcherProvider
    ): DispatcherProvider
}