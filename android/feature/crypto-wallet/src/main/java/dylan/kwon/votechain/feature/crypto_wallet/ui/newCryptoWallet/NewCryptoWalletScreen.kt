@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)

package dylan.kwon.votechain.feature.crypto_wallet.ui.newCryptoWallet

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dylan.kwon.votechain.core.ui.design_system.theme.VoteChainTheme
import dylan.kwon.votechain.core.ui.design_system.theme.composable.loadingActionButton.LoadingActionButton
import dylan.kwon.votechain.feature.crypto_wallet.R
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun NewCryptoWalletRoute(
    viewModel: NewCryptoWalletViewModel = hiltViewModel(),
    onCryptoWalletCreated: () -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if (uiState.cryptoWalletState.isSaved) LaunchedEffect(onCryptoWalletCreated) {
        onCryptoWalletCreated()
    }

    NewCryptoWalletScreen(
        uiState = uiState,
        onRefreshClick = viewModel::refresh,
        onNextClick = viewModel::save,
        onCopyClick = {
            context.copyMnemonic(uiState.mnemonic)
        },
    )
}

@Composable
internal fun NewCryptoWalletScreen(
    uiState: NewCryptoWalletUiState,
    onRefreshClick: () -> Unit,
    onCopyClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.create_new_crypto_wallet)
                    )
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            if (uiState.mnemonic.isLoaded) {
                NextButton(
                    isLoading = uiState.cryptoWalletState.isSaving,
                    onClick = onNextClick
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Mnemonic(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                mnemonic = uiState.mnemonic,
                onRefreshClick = onRefreshClick,
                menus = {
                    if (uiState.isShowingMnemonicMenus) {
                        MnemonicMenus(
                            modifier = Modifier.padding(top = 24.dp),
                            onCopyClick = onCopyClick,
                            onRefreshClick = onRefreshClick,
                        )
                    }
                }
            )
        }
    }
}

@Composable
private fun Mnemonic(
    modifier: Modifier = Modifier,
    mnemonic: NewCryptoWalletUiState.Mnemonic,
    onRefreshClick: () -> Unit,
    menus: (@Composable () -> Unit)? = null
) {
    Crossfade(
        modifier = Modifier.animateContentSize(),
        targetState = mnemonic,
        label = "mnemonic-crossFade",
    ) {
        when (it) {
            is NewCryptoWalletUiState.Mnemonic.Loading -> MnemonicLoading(
                modifier = modifier
            )

            is NewCryptoWalletUiState.Mnemonic.Loaded -> MnemonicLoaded(
                modifier = modifier,
                mnemonic = it,
                menus = menus
            )

            is NewCryptoWalletUiState.Mnemonic.Error -> MnemonicError(
                modifier = modifier,
                onRefreshClick = onRefreshClick
            )
        }
    }
}

@Composable
private fun MnemonicLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.aspectRatio(2 / 1f),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun MnemonicLoaded(
    modifier: Modifier = Modifier,
    mnemonic: NewCryptoWalletUiState.Mnemonic.Loaded,
    menus: (@Composable () -> Unit)? = null
) {
    Column {
        FlowRow(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
        ) {
            repeat(mnemonic.size) { index ->
                MnemonicItem(
                    word = mnemonic.words[index]
                )
            }
        }
        menus?.let {
            it()
        }
    }
}

@Composable
private fun MnemonicError(
    modifier: Modifier = Modifier,
    onRefreshClick: () -> Unit
) {
    Card(
        modifier = modifier
            .width(IntrinsicSize.Min)
            .height(IntrinsicSize.Min)
            .aspectRatio(2 / 1f),
        colors = CardDefaults.cardColors().copy(
            containerColor = MaterialTheme.colorScheme.errorContainer
        )
    ) {
        IconButton(
            modifier = Modifier.fillMaxSize(),
            onClick = onRefreshClick
        ) {
            Icon(
                Icons.Default.Refresh,
                contentDescription = stringResource(id = R.string.refresh)
            )
        }
    }
}

@Composable
private fun MnemonicItem(
    modifier: Modifier = Modifier,
    word: String
) {
    ElevatedCard(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(
                horizontal = 16.dp, vertical = 8.dp
            ),
            text = word
        )
    }
}

@Composable
fun MnemonicMenus(
    modifier: Modifier = Modifier,
    onRefreshClick: () -> Unit,
    onCopyClick: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
    ) {
        RefreshButton(
            onClick = onRefreshClick
        )
        CopyButton(
            onClick = onCopyClick
        )
    }
}

@Composable
private fun RefreshButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.elevatedButtonColors().copy(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
        )
    ) {
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = stringResource(id = R.string.refresh)
        )
    }
}

@Composable
private fun CopyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.elevatedButtonColors().copy(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Icon(
            imageVector = Icons.Default.CopyAll,
            contentDescription = stringResource(id = R.string.copy)
        )
    }
}

@Composable
private fun NextButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
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

private fun Context.copyMnemonic(mnemonic: NewCryptoWalletUiState.Mnemonic) {
    if (mnemonic !is NewCryptoWalletUiState.Mnemonic.Loaded) {
        return
    }
    getSystemService(ClipboardManager::class.java).setPrimaryClip(
        ClipData.newPlainText(
            getString(R.string.mnemonic),
            mnemonic.words.joinToString(separator = " ")
        )
    )
}

@Composable
@Preview(showBackground = true)
private fun LoadingPreview() {
    VoteChainTheme {
        NewCryptoWalletScreen(
            uiState = NewCryptoWalletUiState(
                mnemonic = NewCryptoWalletUiState.Mnemonic.Loading,
            ),
            onRefreshClick = {},
            onNextClick = {},
            onCopyClick = {},
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun LoadedPreview() {
    VoteChainTheme {
        NewCryptoWalletScreen(
            uiState = NewCryptoWalletUiState(
                mnemonic = NewCryptoWalletUiState.Mnemonic.Loaded(
                    persistentListOf(
                        "Hi",
                        "Hello",
                        "Word",
                        "Android",
                        "Kotlin",
                        "BlockChain",
                        "Solidity",
                        "Developer",
                        "Google",
                        "Application",
                        "Mobile",
                        "Project"
                    )
                ),
            ),
            onRefreshClick = {},
            onNextClick = {},
            onCopyClick = {},
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ErrorPreview() {
    VoteChainTheme {
        NewCryptoWalletScreen(
            uiState = NewCryptoWalletUiState(
                mnemonic = NewCryptoWalletUiState.Mnemonic.Error,
            ),
            onRefreshClick = {},
            onNextClick = {},
            onCopyClick = {},
        )
    }
}