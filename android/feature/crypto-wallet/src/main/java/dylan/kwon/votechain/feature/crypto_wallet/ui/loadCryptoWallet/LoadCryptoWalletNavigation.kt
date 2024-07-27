package dylan.kwon.votechain.feature.crypto_wallet.ui.loadCryptoWallet

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object NewCryptoWalletNavigation

fun NavGraphBuilder.attachLoadCryptoWalletScreen(
    onBackClick: () -> Unit,
    onCryptoWalletLoaded: () -> Unit,
) {
    composable<NewCryptoWalletNavigation> {
        LoadCryptoWalletRoute(
            onBackClick = onBackClick,
            onCryptoWalletLoaded = onCryptoWalletLoaded
        )
    }
}

fun NavHostController.navigateToLoadCryptoWallet(
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(NewCryptoWalletNavigation, builder)
}

