package dylan.kwon.votechain.feature.crypto_wallet.ui.newCryptoWallet

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object NewCryptoWalletNavigation

fun NavGraphBuilder.attachNewCryptoWalletScreen(
    onCryptoWalletCreated: () -> Unit
) {
    composable<NewCryptoWalletNavigation> {
        NewCryptoWalletRoute(
            onCryptoWalletCreated = onCryptoWalletCreated
        )
    }
}

fun NavHostController.navigateToNewCryptoWallet(
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(NewCryptoWalletNavigation, builder)
}

