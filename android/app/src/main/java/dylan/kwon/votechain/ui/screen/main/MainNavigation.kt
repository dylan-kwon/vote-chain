package dylan.kwon.votechain.ui.screen.main

import androidx.compose.ui.platform.LocalView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem.VoteListItemUiState
import dylan.kwon.votechain.core.ui.navigation.result.ReceiveResultEffect
import dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.SimplePasswordNavigationResult
import kotlinx.serialization.Serializable

@Serializable
object MainNavigation

fun NavGraphBuilder.attachMainScreen(
    onNeedCryptoWallet: () -> Unit,
    onNeedSimplePasswordVerify: () -> Unit,
    onVoteAddClick: () -> Unit,
    onVoteListItemClick: (VoteListItemUiState) -> Unit
) {
    composable<MainNavigation> { backstackEntry ->
        val viewModel: MainViewModel = hiltViewModel(
            requireNotNull(LocalView.current.findViewTreeViewModelStoreOwner())
        )
        backstackEntry.ReceiveResultEffect<SimplePasswordNavigationResult>(
            SimplePasswordNavigationResult.KEY, viewModel
        ) { result ->
            if (result.isSuccess) {
                viewModel.verifiedSimplePassword()
            }
        }
        MainRoute(
            viewModel = viewModel,
            onNeedCryptoWallet = onNeedCryptoWallet,
            onNeedSimplePasswordVerify = onNeedSimplePasswordVerify,
            onVoteAddClick = onVoteAddClick,
            onVoteListItemClick = onVoteListItemClick
        )
    }
}

fun NavHostController.navigateToMain(
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(MainNavigation, builder)
}

fun NavHostController.popToMain() {
    popBackStack(
        route = MainNavigation,
        inclusive = false
    )
}