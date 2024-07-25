package dylan.kwon.votechain.feature.auth.ui.screen.simplePassword

import android.os.Parcelable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Serializable
data class SimplePasswordNavigation(
    val canBack: Boolean = false,
)

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
        val navigation = it.toRoute<SimplePasswordNavigation>()
        SimplePasswordRoute(
            navigation = navigation,
            onResult = onResult
        )
    }
}

fun NavHostController.navigateToSimplePassword(
    navigation: SimplePasswordNavigation = SimplePasswordNavigation(),
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(navigation, builder)
}

