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
import dylan.kwon.votechain.feature.vote.screen.add.AddVoteNavigationResult
import dylan.kwon.votechain.feature.vote.screen.list.VoteListViewModel
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
        val mainViewModel: MainViewModel = hiltViewModel(
            requireNotNull(LocalView.current.findViewTreeViewModelStoreOwner())
        )
        val voteListViewModel: VoteListViewModel = hiltViewModel()

        MainRoute(
            viewModel = mainViewModel,
            onNeedCryptoWallet = onNeedCryptoWallet,
            onNeedSimplePasswordVerify = onNeedSimplePasswordVerify,
            onVoteAddClick = onVoteAddClick,
            onVoteListItemClick = onVoteListItemClick
        )

        backstackEntry.ReceiveResultEffect<SimplePasswordNavigationResult>(
            SimplePasswordNavigationResult.KEY, mainViewModel
        ) { result ->
            if (result.isSuccess) {
                mainViewModel.verifiedSimplePassword()
            }
        }

        backstackEntry.ReceiveResultEffect<AddVoteNavigationResult>(
            AddVoteNavigationResult.KEY, voteListViewModel
        ) { result ->
            if (result.isCreated) {
                voteListViewModel.refresh()
            }
        }
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