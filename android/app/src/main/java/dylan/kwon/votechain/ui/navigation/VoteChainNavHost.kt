package dylan.kwon.votechain.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import dylan.kwon.votechain.feature.auth.ui.navigation.attachAuthNavigation
import dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.SimplePasswordNavigation
import dylan.kwon.votechain.ui.VoteChainAppState
import dylan.kwon.votechain.ui.screen.main.attachMainScreen

@Composable
fun VoteChainNavHost(
    modifier: Modifier = Modifier,
    appState: VoteChainAppState,
    startDestination: Any = SimplePasswordNavigation
) {
    NavHost(
        modifier = modifier,
        navController = appState.navController,
        startDestination = startDestination
    ) {
        attachMainScreen()
        attachAuthNavigation()
    }
}