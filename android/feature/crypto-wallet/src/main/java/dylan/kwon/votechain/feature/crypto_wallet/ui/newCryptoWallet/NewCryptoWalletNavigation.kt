package dylan.kwon.votechain.feature.crypto_wallet.ui.newCryptoWallet

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object NewCryptoWalletNavigation

fun NavGraphBuilder.attachNewCryptoWalletScreen(
    onBackClick: () -> Unit,
    onCryptoWalletCreated: () -> Unit
) {
    composable<NewCryptoWalletNavigation> {
        NewCryptoWalletRoute(
            onBackClick = onBackClick,
            onCryptoWalletCreated = onCryptoWalletCreated
        )
    }
}

fun NavHostController.navigateToNewCryptoWallet(
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(NewCryptoWalletNavigation, builder)
}

