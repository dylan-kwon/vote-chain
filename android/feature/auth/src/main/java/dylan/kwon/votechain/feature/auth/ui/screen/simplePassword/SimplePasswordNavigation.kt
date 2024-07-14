package dylan.kwon.votechain.feature.auth.ui.screen.simplePassword

import android.os.Parcelable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Serializable
object SimplePasswordNavigation

@Parcelize
data class SimplePasswordNavigationResult(
    val isSuccess: Boolean
) : Parcelable {
    companion object {
        val KEY = requireNotNull(this::class.qualifiedName)
    }
}

fun NavGraphBuilder.attachSimplePasswordScreen(
    onResult: (SimplePasswordNavigationResult) -> Unit
) {
    composable<SimplePasswordNavigation> {
        SimplePasswordRoute(
            onResult = onResult
        )
    }
}

fun NavHostController.navigateToSimplePassword(
    navOptions: NavOptions? = null,
) {
    navigate(SimplePasswordNavigation, navOptions)
}

