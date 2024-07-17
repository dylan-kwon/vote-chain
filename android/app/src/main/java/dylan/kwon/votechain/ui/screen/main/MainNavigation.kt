package dylan.kwon.votechain.ui.screen.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object MainNavigation

fun NavGraphBuilder.attachMainScreen(
    onNavigateToAddCryptoWallet: () -> Unit
) {
    composable<MainNavigation> {
        MainRoute(
            onNavigateToAddCryptoWallet = onNavigateToAddCryptoWallet
        )
    }
}

fun NavHostController.navigateToMain(
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(MainNavigation, builder)
}

