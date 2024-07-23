package dylan.kwon.votechain.feature.vote.screen.add

import android.os.Parcelable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
object AddVoteNavigation

@Parcelize
data class AddVoteNavigationResult(
    val isCreated: Boolean
) : Parcelable {
    companion object {
        val KEY = requireNotNull(this::class.qualifiedName)
    }
}

fun NavGraphBuilder.attachAddVoteScreen(
    onBackClick: () -> Unit,
    onResult: (AddVoteNavigationResult) -> Unit
) {
    composable<AddVoteNavigation> {
        AddVoteRoute(
            onBackClick = onBackClick,
            onResult = onResult
        )
    }
}

fun NavHostController.navigateToAddVote(
    navigation: AddVoteNavigation = AddVoteNavigation,
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(navigation, builder)
}

