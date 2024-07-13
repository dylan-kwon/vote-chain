package dylan.kwon.votechain.feature.auth.ui.screen.simplePassword

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
object SimplePasswordNavigation

@Serializable
data class SimplePasswordNavigationResult(
    val isSuccess: Boolean
)

fun NavGraphBuilder.attachSimplePasswordScreen() {
    composable<SimplePasswordNavigation> {
        SimplePasswordRoute()
    }
}

fun NavHostController.navigateToSimplePassword(
    navOptions: NavOptions? = null,
) {
    navigate(SimplePasswordNavigation, navOptions)
}

