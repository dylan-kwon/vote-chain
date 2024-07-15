package dylan.kwon.votechain.feature.crypto_wallet.ui.addCryptoWallet

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
object AddCryptoWalletNavigation


fun NavGraphBuilder.attachAddCryptoWalletScreen(
    onCreateClick: () -> Unit,
    onLoadClick: () -> Unit,
) {
    composable<AddCryptoWalletNavigation> {
        AddCryptoWalletRoute(
            onCreateClick = onCreateClick,
            onLoadClick = onLoadClick
        )
    }
}

fun NavHostController.navigateToAddCryptoWallet(
    navOptions: NavOptions? = null,
) {
    navigate(AddCryptoWalletNavigation, navOptions)
}

