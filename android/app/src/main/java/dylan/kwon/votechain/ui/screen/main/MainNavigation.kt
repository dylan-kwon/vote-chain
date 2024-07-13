package dylan.kwon.votechain.ui.screen.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object MainNavigation

fun NavGraphBuilder.attachMainScreen() {
    composable<MainNavigation> {
        MainRoute()
    }
}

fun NavHostController.navigateToMain(
    navOptions: NavOptions? = null,
) {
    navigate(MainNavigation, navOptions)
}

