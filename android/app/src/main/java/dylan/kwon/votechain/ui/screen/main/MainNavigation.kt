package dylan.kwon.votechain.ui.screen.main

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import dylan.kwon.votechain.core.ui.compose_ext.findActivity
import dylan.kwon.votechain.core.ui.navigation.result.ReceiveResultEffect
import dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.SimplePasswordNavigationResult
import kotlinx.serialization.Serializable

@Serializable
object MainNavigation

fun NavGraphBuilder.attachMainScreen(
    onNeedCryptoWallet: () -> Unit,
    onNeedSimplePasswordVerify: () -> Unit,
) {
    composable<MainNavigation> { backstackEntry ->
        val viewModel: MainViewModel = hiltViewModel(
            LocalContext.current.findActivity() as ComponentActivity
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