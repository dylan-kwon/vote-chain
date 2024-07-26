package dylan.kwon.votechain.core.coroutine.test.android.dispatcher

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import dylan.kwon.votechain.core.coroutine.jvm.dispatcher.DispatcherProvider
import dylan.kwon.votechain.core.coroutine.jvm.dispatcher.DispatcherProviderModule
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatcherProviderModule::class],
)
abstract class TestDispatcherProviderModule {

    companion object {
        @Provides
        @Singleton
        fun providesTestDispatcher() = UnconfinedTestDispatcher()
    }

    @Binds
    @Singleton
    abstract fun bindsTestDispatcherProvider(
        dispatcherProvider: TestDispatcherProvider
    ): DispatcherProvider
}