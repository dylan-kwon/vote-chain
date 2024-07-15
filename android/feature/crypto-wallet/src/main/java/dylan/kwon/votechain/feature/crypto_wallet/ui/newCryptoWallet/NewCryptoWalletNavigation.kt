package dylan.kwon.votechain.feature.crypto_wallet.ui.newCryptoWallet

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object NewCryptoWalletNavigation

fun NavGraphBuilder.attachNewCryptoWalletScreen(
    onNextClick: () -> Unit,
) {
    composable<NewCryptoWalletNavigation> {
        NewCryptoWalletRoute(
            onNextClick = onNextClick
        )
    }
}

fun NavHostController.navigateToNewCryptoWallet(
    navOptions: NavOptions? = null,
) {
    navigate(NewCryptoWalletNavigation, navOptions)
}

