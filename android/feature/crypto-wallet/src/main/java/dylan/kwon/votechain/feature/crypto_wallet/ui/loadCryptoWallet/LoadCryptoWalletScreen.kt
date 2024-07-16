@file:OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package dylan.kwon.votechain.feature.crypto_wallet.ui.loadCryptoWallet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dylan.kwon.votechain.core.ui.design_system.theme.VoteChainTheme
import dylan.kwon.votechain.core.ui.design_system.theme.composable.loadingActionButton.LoadingActionButton
import dylan.kwon.votechain.feature.crypto_wallet.R

@Composable
internal fun LoadCryptoWalletRoute(
    viewModel: LoadCryptoWalletViewModel = hiltViewModel(),
    onCryptoWalletLoaded: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if(uiState.cryptoWalletState.isLoaded) LaunchedEffect(onCryptoWalletLoaded) {
        onCryptoWalletLoaded()
    }

    LoadCryptoWalletScreen(
        uiState = uiState,
        onMnemonicChange = viewModel::updateMnemonic,
        onLoadClick = viewModel::loadCryptoWallet
    )
}

@Composable
internal fun LoadCryptoWalletScreen(
    uiState: LoadCryptoWalletUiState,
    onMnemonicChange: (String) -> Unit,
    onLoadClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(
            scrollBehavior.nestedScrollConnection
        ),
        topBar = {
            TopBar(scrollBehavior = scrollBehavior)
        },
        floatingActionButton = {
            if (uiState.mnemonicState.isValid) {
                LoadButton(
                    isLoading = uiState.cryptoWalletState.isLoading,
                    onClick = onLoadClick,
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Mnemonic(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 200.dp),
                words = uiState.mnemonic,
                isValid = !uiState.mnemonicState.isInvalid,
                onChange = onMnemonicChange,
            )
        }
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = stringResource(id = R.string.load_crypto_wallet))
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun Mnemonic(
    modifier: Modifier = Modifier,
    words: String,
    isValid: Boolean,
    onChange: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = modifier,
        value = words,
        isError = !isValid,
        onValueChange = onChange,
        placeholder = {
            Text(text = stringResource(id = R.string.mnemonic_input_hint))
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
    )
}

@Composable
private fun LoadButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    onClick: () -> Unit
) {
    LoadingActionButton(
        modifier = modifier,
        isLoading = isLoading,
        icon = {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = stringResource(id = R.string.next),
            )
        },
        onClick = onClick
    )
}

@Composable
@Preview(showBackground = true)
private fun ErrorPreview() {
    VoteChainTheme {
        LoadCryptoWalletScreen(
            uiState = LoadCryptoWalletUiState(),
            onMnemonicChange = {},
            onLoadClick = {}
        )
    }
}