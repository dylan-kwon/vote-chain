package dylan.kwon.votechain.feature.settings.ui.settings

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object SettingsNavigation

fun NavGraphBuilder.attachSettingsScreen(
    onBackClick: () -> Unit,
    onPrivateKeyShowClick: () -> Unit,
    content: @Composable (NavBackStackEntry, viewModel: SettingsViewModel) -> Unit,
) {
    composable<SettingsNavigation> {
        val viewModel = hiltViewModel<SettingsViewModel>()

        content(it, viewModel)

        SettingsRoute(
            onBackClick = onBackClick,
            onPrivateKeyShowClick = onPrivateKeyShowClick
        )
    }
}

fun NavHostController.navigateToSettings(
    navigation: SettingsNavigation = SettingsNavigation,
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(navigation, builder)
}
