package dylan.kwon.votechain.feature.settings.ui.settings.uiMapper

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DefaultSettingsUiMapperModule {

    @Binds
    @ViewModelScoped
    abstract fun bindsDefaultSettingsUiMapper(
        uiMapper: DefaultSettingsUiMapper
    ): SettingsUiMapper

}