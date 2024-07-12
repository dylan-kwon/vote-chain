package dylan.kwon.votechain.core.coroutine.android.di//package dylan.kwon.auth.ui.screen.simplePassword

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import dylan.kwon.votechain.core.coroutine.android.dispatcher.DispatcherProvider
import dylan.kwon.votechain.core.coroutine.android.dispatcher.TestDispatcherProvider
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