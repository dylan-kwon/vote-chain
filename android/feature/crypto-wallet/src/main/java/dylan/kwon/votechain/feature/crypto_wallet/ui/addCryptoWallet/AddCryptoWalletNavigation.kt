package dylan.kwon.votechain.feature.crypto_wallet.ui.addCryptoWallet

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable


@Serializable
data class AddCryptoWalletNavigation(
    val isBackToFinish: Boolean = true
)


fun NavGraphBuilder.attachAddCryptoWalletScreen(
    onCreateClick: () -> Unit,
    onLoadClick: () -> Unit,
) {
    composable<AddCryptoWalletNavigation> {
        AddCryptoWalletRoute(
            navigation = it.toRoute<AddCryptoWalletNavigation>(),
            onCreateClick = onCreateClick,
            onLoadClick = onLoadClick
        )
    }
}

fun NavHostController.navigateToAddCryptoWallet(
    navigation: AddCryptoWalletNavigation = AddCryptoWalletNavigation(),
    builder: NavOptionsBuilder.() -> Unit = {},
) {
    navigate(navigation, builder)
}

