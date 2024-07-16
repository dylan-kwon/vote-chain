package dylan.kwon.votechain.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import dylan.kwon.votechain.core.ui.navigation.result.setResult
import dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.SimplePasswordNavigationResult
import dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.attachSimplePasswordScreen
import dylan.kwon.votechain.feature.crypto_wallet.ui.addCryptoWallet.AddCryptoWalletNavigation
import dylan.kwon.votechain.feature.crypto_wallet.ui.addCryptoWallet.attachAddCryptoWalletScreen
import dylan.kwon.votechain.feature.crypto_wallet.ui.addCryptoWallet.navigateToAddCryptoWallet
import dylan.kwon.votechain.feature.crypto_wallet.ui.loadCryptoWallet.attachLoadCryptoWalletScreen
import dylan.kwon.votechain.feature.crypto_wallet.ui.loadCryptoWallet.navigateToLoadCryptoWallet
import dylan.kwon.votechain.feature.crypto_wallet.ui.newCryptoWallet.attachNewCryptoWalletScreen
import dylan.kwon.votechain.feature.crypto_wallet.ui.newCryptoWallet.navigateToNewCryptoWallet
import dylan.kwon.votechain.ui.VoteChainAppState
import dylan.kwon.votechain.ui.screen.main.attachMainScreen

@Composable
fun VoteChainNavHost(
    modifier: Modifier = Modifier,
    appState: VoteChainAppState,
    startDestination: Any = AddCryptoWalletNavigation
) {
    NavHost(
        modifier = modifier,
        navController = appState.navController,
        startDestination = startDestination
    ) {
        attachMainScreen {
            appState.navController.navigateToAddCryptoWallet()
        }

        attachSimplePasswordScreen(
            onResult = { result ->
                appState.navController.setResult(SimplePasswordNavigationResult.KEY, result)
                appState.navController.popBackStack()
            }
        )

        attachAddCryptoWalletScreen(
            onCreateClick = {
                appState.navController.navigateToNewCryptoWallet()
            },
            onLoadClick = {
                appState.navController.navigateToLoadCryptoWallet()
            }
        )

        attachNewCryptoWalletScreen(
            onCryptoWalletCreated = {
                appState.navController.popBackStack()
            }
        )

        attachLoadCryptoWalletScreen(
            onCryptoWalletLoaded = {
                appState.navController.popBackStack()
            }
        )
    }
}