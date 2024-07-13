package dylan.kwon.votechain.core.coroutine.jvm.dispatcher

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dylan.kwon.votechain.core.coroutine.jvm.dispatcher.DefaultDispatcherProvider
import dylan.kwon.votechain.core.coroutine.jvm.dispatcher.DispatcherProvider
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