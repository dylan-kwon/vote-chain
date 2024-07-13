package dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.uiMapper

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SimplePasswordUiMapperModule {
    @Binds
    @Singleton
    abstract fun bindsDefaultSimplePasswordUiMapper(
        simplePasswordUiMapper: DefaultSimplePasswordUiMapper
    ): SimplePasswordUiMapper
}
