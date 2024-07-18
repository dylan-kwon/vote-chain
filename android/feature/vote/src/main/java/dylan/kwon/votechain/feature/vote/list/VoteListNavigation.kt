package dylan.kwon.votechain.feature.vote.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object VoteListNavigation


fun NavGraphBuilder.attachVoteListScreen() {
    composable<VoteListNavigation> {
        VoteListRoute()
    }
}

fun NavHostController.navigateToVoteList(
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(VoteListNavigation, builder)
}

