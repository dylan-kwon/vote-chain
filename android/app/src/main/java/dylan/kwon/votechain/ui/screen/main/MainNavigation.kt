package dylan.kwon.votechain.ui.screen.main

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object MainNavigation

fun NavGraphBuilder.attachMainScreen(go: () -> Unit) {
    composable<MainNavigation> {
        MainRoute()
        LaunchedEffect(Unit) {
            go()
        }
    }
}

fun NavHostController.navigateToMain(
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(MainNavigation, builder)
}

