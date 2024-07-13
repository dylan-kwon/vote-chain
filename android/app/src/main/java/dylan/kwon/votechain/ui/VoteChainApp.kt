package dylan.kwon.votechain.ui

import androidx.compose.runtime.Composable
import dylan.kwon.votechain.core.ui.design_system.theme.VoteChainTheme
import dylan.kwon.votechain.ui.navigation.VoteChainNavHost

@Composable
fun VoteChainApp(
    appState: VoteChainAppState = rememberAppState()
) {
    VoteChainTheme {
        VoteChainNavHost(appState = appState)
    }
}