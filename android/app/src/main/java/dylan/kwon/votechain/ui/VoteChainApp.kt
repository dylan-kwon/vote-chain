package dylan.kwon.votechain.ui

import androidx.compose.runtime.Composable
import dylan.kwon.votechain.ui.navigation.VoteChainNavHost
import dylan.kwon.votechain.ui.theme.VoteChainTheme

@Composable
fun VoteChainApp(
    appState: VoteChainAppState = rememberAppState()
) {
    VoteChainTheme {
        VoteChainNavHost(appState = appState)
    }
}