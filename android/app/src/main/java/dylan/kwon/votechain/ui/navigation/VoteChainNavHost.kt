package dylan.kwon.votechain.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import dylan.kwon.votechain.ui.VoteChainAppState
import dylan.kwon.votechain.ui.screen.main.MainNavigation
import dylan.kwon.votechain.ui.screen.main.mainScreen

@Composable
fun VoteChainNavHost(
    modifier: Modifier = Modifier,
    appState: VoteChainAppState,
    startDestination: Any = MainNavigation
) {
    NavHost(
        modifier = modifier,
        navController = appState.navController,
        startDestination = startDestination
    ) {
        mainScreen()
    }
}