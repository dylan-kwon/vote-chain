package dylan.kwon.votechain.feature.vote.screen.list

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem.VoteListItemUiState
import dylan.kwon.votechain.core.ui.navigation.result.ReceiveResultEffect
import dylan.kwon.votechain.feature.vote.screen.add.AddVoteNavigationResult
import kotlinx.serialization.Serializable

@Serializable
object VoteListNavigation


fun NavGraphBuilder.attachVoteListScreen(
    onSettingsClick: () -> Unit,
    onVoteAddClick: () -> Unit,
    onVoteListItemClick: (VoteListItemUiState) -> Unit
) {
    composable<VoteListNavigation> {
        val viewModel = hiltViewModel<VoteListViewModel>()
        val voteListItems = viewModel.voteList.collectAsLazyPagingItems()

        VoteListRoute(
            viewModel = viewModel,
            voteListItems = voteListItems,
            onSettingsClick = onSettingsClick,
            onVoteAddClick = onVoteAddClick,
            onVoteListItemClick = onVoteListItemClick
        )

        it.ReceiveResultEffect<AddVoteNavigationResult>(
            AddVoteNavigationResult.KEY, viewModel, voteListItems
        ) { result ->
            if (result.isCreated) {
                voteListItems.refresh()
            }
        }
    }
}

fun NavHostController.navigateToVoteList(
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(VoteListNavigation, builder)
}

