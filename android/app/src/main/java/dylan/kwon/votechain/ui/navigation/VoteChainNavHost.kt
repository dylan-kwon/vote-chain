@file:OptIn(ExperimentalSharedTransitionApi::class)

package dylan.kwon.votechain.ui.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import dylan.kwon.votechain.core.ui.navigation.result.ReceiveResultEffect
import dylan.kwon.votechain.core.ui.navigation.result.setResult
import dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.SimplePasswordNavigation
import dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.SimplePasswordNavigationResult
import dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.attachSimplePasswordScreen
import dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.navigateToSimplePassword
import dylan.kwon.votechain.feature.crypto_wallet.ui.addCryptoWallet.attachAddCryptoWalletScreen
import dylan.kwon.votechain.feature.crypto_wallet.ui.addCryptoWallet.navigateToAddCryptoWallet
import dylan.kwon.votechain.feature.crypto_wallet.ui.loadCryptoWallet.attachLoadCryptoWalletScreen
import dylan.kwon.votechain.feature.crypto_wallet.ui.loadCryptoWallet.navigateToLoadCryptoWallet
import dylan.kwon.votechain.feature.crypto_wallet.ui.newCryptoWallet.attachNewCryptoWalletScreen
import dylan.kwon.votechain.feature.crypto_wallet.ui.newCryptoWallet.navigateToNewCryptoWallet
import dylan.kwon.votechain.feature.settings.ui.settings.attachSettingsScreen
import dylan.kwon.votechain.feature.settings.ui.settings.navigateToSettings
import dylan.kwon.votechain.feature.vote.screen.add.AddVoteNavigationResult
import dylan.kwon.votechain.feature.vote.screen.add.attachAddVoteScreen
import dylan.kwon.votechain.feature.vote.screen.add.navigateToAddVote
import dylan.kwon.votechain.feature.vote.screen.detail.VoteDetailNavigation
import dylan.kwon.votechain.feature.vote.screen.detail.attachVoteDetailScreen
import dylan.kwon.votechain.feature.vote.screen.detail.navigateToVoteDetail
import dylan.kwon.votechain.feature.vote.screen.list.attachVoteListScreen
import dylan.kwon.votechain.ui.VoteChainAppState
import dylan.kwon.votechain.ui.screen.main.MainNavigation
import dylan.kwon.votechain.ui.screen.main.attachMainScreen
import dylan.kwon.votechain.ui.screen.main.popToMain

@Composable
fun VoteChainNavHost(
    modifier: Modifier = Modifier,
    appState: VoteChainAppState,
    startDestination: Any = MainNavigation
) {
    val navController = appState.navController

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        attachMainScreen(
            onNeedCryptoWallet = {
                navController.navigateToAddCryptoWallet()
            },
            onNeedSimplePasswordVerify = {
                navController.navigateToSimplePassword()
            },
            onSettingsClick = {
                navController.navigateToSettings()
            },
            onVoteAddClick = {
                navController.navigateToAddVote()
            },
            onVoteListItemClick = { voteListItemUiState ->
                navController.navigateToVoteDetail(
                    navigation = VoteDetailNavigation(voteListItemUiState.id)
                )
            }
        )

        attachSimplePasswordScreen(
            onResult = { result ->
                navController.setResult(SimplePasswordNavigationResult.KEY, result)
                navController.popBackStack()
            }
        )

        attachAddCryptoWalletScreen(
            onCreateClick = {
                navController.navigateToNewCryptoWallet()
            },
            onLoadClick = {
                navController.navigateToLoadCryptoWallet()
            }
        )

        attachNewCryptoWalletScreen(
            onBackClick = navController::popBackStack,
            onCryptoWalletCreated = navController::popToMain
        )

        attachLoadCryptoWalletScreen(
            onBackClick = navController::popBackStack,
            onCryptoWalletLoaded = navController::popToMain
        )

        attachVoteListScreen(
            onSettingsClick = {
                navController.navigateToSettings()
            },
            onVoteAddClick = {
                navController.navigateToAddVote()
            },
            onVoteListItemClick = { voteListItemUiState ->
                navController.navigateToVoteDetail(
                    navigation = VoteDetailNavigation(voteListItemUiState.id)
                )
            }
        )

        attachVoteDetailScreen(
            onBackClick = navController::popBackStack,
        )

        attachAddVoteScreen(
            onBackClick = navController::popBackStack,
            onResult = { result ->
                navController.setResult(AddVoteNavigationResult.KEY, result)
                navController.popBackStack()
            }
        )

        attachSettingsScreen(
            onBackClick = navController::popBackStack,
            onPrivateKeyShowClick = {
                navController.navigateToSimplePassword(
                    navigation = SimplePasswordNavigation(canBack = true)
                )
            }
        ) { navBackStackEntry, viewModel ->
            navBackStackEntry.ReceiveResultEffect<SimplePasswordNavigationResult>(
                resultKey = SimplePasswordNavigationResult.KEY, viewModel
            ) {
                if (it.isSuccess) {
                    viewModel.verified()

                }
            }
        }
    }
}