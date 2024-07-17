package dylan.kwon.votechain.feature.crypto_wallet.ui.addCryptoWallet

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import dylan.kwon.votechain.core.ui.design_system.theme.VoteChainTheme
import dylan.kwon.votechain.feature.crypto_wallet.R

@Composable
internal fun AddCryptoWalletRoute(
    viewModel: AddCryptoWalletViewModel = hiltViewModel(),
    onCreateClick: () -> Unit,
    onLoadClick: () -> Unit,
) {
    // val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AddCryptoWalletScreen(
        onCreateClick = onCreateClick,
        onLoadClick = onLoadClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddCryptoWalletScreen(
    orientation: Int = LocalConfiguration.current.orientation,
    onCreateClick: () -> Unit,
    onLoadClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.crypto_wallet)
                    )
                },
            )
        }
    ) { paddingValues ->
        val modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 24.dp)


        when (orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> LandscapeContent(
                modifier = modifier,
                onCreateClick = onCreateClick,
                onLoadClick = onLoadClick
            )

            else -> PortraitContent(
                modifier = modifier,
                onCreateClick = onCreateClick,
                onLoadClick = onLoadClick
            )

        }
    }
}

@Composable
private fun PortraitContent(
    modifier: Modifier = Modifier,
    onCreateClick: () -> Unit,
    onLoadClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CryptoWalletAnim(
            modifier = Modifier.weight(1f)
        )
        Spacer(
            modifier = Modifier.height(56.dp)
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            CreateButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onCreateClick
            )
            Spacer(
                modifier = Modifier.height(4.dp)
            )
            LoadButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onLoadClick
            )
        }
    }
}

@Composable
private fun LandscapeContent(
    modifier: Modifier = Modifier,
    onCreateClick: () -> Unit,
    onLoadClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CryptoWalletAnim(
            modifier = Modifier
                .weight(1f)
                .offset(y = (-64).dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(horizontal = 56.dp),
            verticalArrangement = Arrangement.Center
        ) {
            CreateButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onCreateClick
            )
            Spacer(
                modifier = Modifier.height(4.dp)
            )
            LoadButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onLoadClick
            )
        }
    }
}

@Composable
private fun CryptoWalletAnim(
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.crypto_wallet_lottie),
    )
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        contentScale = ContentScale.Inside,
        iterations = LottieConstants.IterateForever,
    )
}

@Composable
private fun CreateButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ActionButton(
        modifier = modifier,
        text = stringResource(id = R.string.create),
        onClick = onClick
    )
}

@Composable
private fun LoadButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ActionButton(
        modifier = modifier,
        text = stringResource(id = R.string.load),
        onClick = onClick
    )
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(text = text)
    }
}

@Composable
@Preview(showBackground = true)
private fun PortraitPreview() {
    VoteChainTheme {
        AddCryptoWalletScreen(
            orientation = Configuration.ORIENTATION_PORTRAIT,
            onCreateClick = {},
            onLoadClick = {}
        )
    }
}

@Composable
@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p)
private fun LandscapePreview() {
    VoteChainTheme {
        AddCryptoWalletScreen(
            orientation = Configuration.ORIENTATION_LANDSCAPE,
            onCreateClick = {},
            onLoadClick = {}
        )
    }
}
