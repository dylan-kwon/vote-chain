package dylan.kwon.votechain.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import dylan.kwon.votechain.core.ui.navigation.result.setResult
import dylan.kwon.votechain.feature.auth.ui.navigation.attachAuthNavigation
import dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.SimplePasswordNavigation
import dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.SimplePasswordNavigationResult
import dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.navigateToSimplePassword
import dylan.kwon.votechain.ui.VoteChainAppState
import dylan.kwon.votechain.ui.screen.main.MainNavigation
import dylan.kwon.votechain.ui.screen.main.attachMainScreen

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
        attachMainScreen {
            appState.navController.navigateToSimplePassword()
        }

        attachAuthNavigation(
            onSimplePasswordResult = { result ->
                appState.navController.setResult(SimplePasswordNavigationResult.KEY, result)
                appState.navController.popBackStack()
            }
        )
    }
}