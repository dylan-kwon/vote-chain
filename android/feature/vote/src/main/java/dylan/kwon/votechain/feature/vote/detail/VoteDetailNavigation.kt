@file:OptIn(ExperimentalSharedTransitionApi::class)

package dylan.kwon.votechain.feature.vote.detail

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class VoteDetailNavigation(
    val id: Long
)

fun NavGraphBuilder.attachVoteDetailScreen(
    onBackClick: () -> Unit
) {
    composable<VoteDetailNavigation> {
        VoteDetailRoute(
            onBackClick = onBackClick,
        )
    }
}

fun NavHostController.navigateToVoteDetail(
    navigation: VoteDetailNavigation,
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(navigation, builder)
}

