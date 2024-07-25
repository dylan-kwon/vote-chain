@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)

package dylan.kwon.votechain.feature.settings.ui.settings

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.valentinilk.shimmer.shimmer
import dylan.kwon.votechain.core.ui.design_system.theme.VoteChainTheme
import dylan.kwon.votechain.feature.settings.R
import dylan.kwon.votechain.feature.settings.ui.settings.preview.SettingsUiStatePreviewParameterProvider

@Composable
internal fun SettingsRoute(
    viewModel: SettingsViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onPrivateKeyShowClick: () -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    SettingsScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onPrivateKeyShowClick = onPrivateKeyShowClick,
        onCopyClick = {
            context.copyToClipboard(it)
        }
    )
}

@Composable
internal fun SettingsScreen(
    modifier: Modifier = Modifier,
    uiState: SettingsUiState,
    onBackClick: () -> Unit,
    onPrivateKeyShowClick: () -> Unit,
    onCopyClick: (String) -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopBar(
                onBackClick = onBackClick,
            )
        }
    ) { paddingValues ->
        val bodyModifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp, vertical = 24.dp)

        AnimatedContent(
            targetState = uiState,
            label = "settings-screen",
            contentKey = {
                it::class
            },
        ) { target ->
            when (target) {
                is SettingsUiState.Loading -> {
                    LoadingScreen(
                        modifier = bodyModifier,
                    )
                }

                is SettingsUiState.Loaded -> {
                    LoadedScreen(
                        modifier = bodyModifier,
                        uiState = target,
                        onPrivateKeyShowClick = onPrivateKeyShowClick,
                        onCopyClick = onCopyClick
                    )
                }
            }
        }
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = R.string.settings)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.go_back)
                )
            }
        }
    )
}

@Composable
private fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.shimmer(),
    ) {
        Placeholder(
            modifier = Modifier.size(96.dp, 32.dp)
        )
        Placeholder(
            modifier = Modifier
                .size(240.dp, 40.dp)
                .padding(top = 4.dp)
        )
        Placeholder(
            modifier = Modifier
                .size(120.dp, 64.dp)
                .padding(top = 24.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
internal fun Placeholder(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.surfaceDim)
    )
}

@Composable
private fun LoadedScreen(
    modifier: Modifier = Modifier,
    uiState: SettingsUiState.Loaded,
    onPrivateKeyShowClick: () -> Unit,
    onCopyClick: (String) -> Unit
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        LabelAndValue(
            label = stringResource(id = R.string.crypto_wallet_address),
            value = uiState.cryptoWallet.address,
            onCopyClick = onCopyClick,
        )

        AnimatedVisibility(
            visible = uiState.isPrivateKeyVisible,
        ) {
            LabelAndValue(
                label = stringResource(id = R.string.private_key),
                value = uiState.cryptoWallet.privateKey.orEmpty(),
                onCopyClick = onCopyClick,
            )
        }

        if (uiState.isPrivateKeyShowButtonVisible) {
            PrivateKeyShowButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onPrivateKeyShowClick
            )
        }
    }
}

@Composable
private fun LabelAndValue(
    label: String,
    value: String,
    onCopyClick: (String) -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge
            )
            IconButton(onClick = { onCopyClick(value) }) {
                Icon(
                    Icons.Default.CopyAll,
                    contentDescription = stringResource(id = R.string.copy)
                )
            }
        }
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun PrivateKeyShowButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = stringResource(id = R.string.show_private_key)
        )
    }
}

private fun Context.copyToClipboard(string: String) {
    getSystemService(ClipboardManager::class.java).setPrimaryClip(
        ClipData.newPlainText(
            getString(R.string.copy),
            string
        )
    )
}

@Composable
@Preview(showBackground = true)
private fun Preview(
    @PreviewParameter(SettingsUiStatePreviewParameterProvider::class)
    uiState: SettingsUiState
) {
    VoteChainTheme {
        SettingsScreen(
            uiState = uiState,
            onBackClick = {},
            onPrivateKeyShowClick = {},
            onCopyClick = {}
        )
    }
}