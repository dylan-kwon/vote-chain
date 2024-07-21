package dylan.kwon.votechain.feature.vote.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem.VoteListItemUiState
import kotlinx.serialization.Serializable

@Serializable
object VoteListNavigation


fun NavGraphBuilder.attachVoteListScreen(
    onVoteAddClick: () -> Unit,
    onVoteListItemClick: (VoteListItemUiState) -> Unit
) {
    composable<VoteListNavigation> {
        VoteListRoute(
            onVoteAddClick = onVoteAddClick,
            onVoteListItemClick = onVoteListItemClick
        )
    }
}

fun NavHostController.navigateToVoteList(
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(VoteListNavigation, builder)
}

