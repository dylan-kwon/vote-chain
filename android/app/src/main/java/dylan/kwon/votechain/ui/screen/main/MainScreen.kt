package dylan.kwon.votechain.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dylan.kwon.votechain.R
import dylan.kwon.votechain.core.ui.compose_ext.composable.OneShotLaunchedEffect
import dylan.kwon.votechain.core.ui.compose_ext.extension.findActivity
import dylan.kwon.votechain.core.ui.design_system.theme.VoteChainTheme
import dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem.VoteListItemUiState
import dylan.kwon.votechain.feature.vote.screen.list.VoteListRoute

@Composable
internal fun MainRoute(
    viewModel: MainViewModel = hiltViewModel(),
    onNeedCryptoWallet: () -> Unit,
    onNeedSimplePasswordVerify: () -> Unit,
    onVoteAddClick: () -> Unit,
    onVoteListItemClick: (VoteListItemUiState) -> Unit
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MainScreen(
        uiState = uiState,
        onExitClick = {
            context.findActivity().finish()
        },
        onResumeWhenNoSetup = viewModel::setup,
        onNeedCryptoWallet = onNeedCryptoWallet,
        onNeedSimplePasswordVerify = onNeedSimplePasswordVerify,
        onVoteAddClick = onVoteAddClick,
        onVoteListItemClick = onVoteListItemClick,
    )
}

@Composable
internal fun MainScreen(
    uiState: MainUiState,
    onExitClick: () -> Unit,
    onNeedSimplePasswordVerify: () -> Unit,
    onResumeWhenNoSetup: () -> Unit,
    onNeedCryptoWallet: () -> Unit,
    onVoteAddClick: () -> Unit,
    onVoteListItemClick: (VoteListItemUiState) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        val modifier = Modifier.fillMaxSize()

        when (uiState) {
            is MainUiState.Setup -> Setup(
                modifier = modifier,
                uiState = uiState,
                onNeedSimplePasswordVerify = onNeedSimplePasswordVerify,
                onVoteAddClick = onVoteAddClick,
                onVoteListItemClick = onVoteListItemClick,
            )

            is MainUiState.NoSetup -> NoSetup(
                modifier = modifier,
                uiState = uiState,
                onResume = onResumeWhenNoSetup,
                onNeedCryptoWallet = onNeedCryptoWallet
            )

            is MainUiState.Error -> Error(
                modifier = modifier,
                uiState = uiState,
                onExitClick = onExitClick
            )
        }
    }
}

@Composable
private fun Setup(
    modifier: Modifier = Modifier,
    uiState: MainUiState.Setup,
    onNeedSimplePasswordVerify: () -> Unit,
    onVoteAddClick: () -> Unit,
    onVoteListItemClick: (VoteListItemUiState) -> Unit
) {
    when (uiState.isVerifiedSimplePassword) {
        true -> VoteListRoute(
            modifier = modifier,
            onVoteAddClick = onVoteAddClick,
            onVoteListItemClick = onVoteListItemClick
        )

        else -> OneShotLaunchedEffect(Unit) {
            onNeedSimplePasswordVerify()
        }
    }
}

@Composable
private fun NoSetup(
    modifier: Modifier = Modifier,
    uiState: MainUiState.NoSetup,
    onResume: () -> Unit,
    onNeedCryptoWallet: () -> Unit
) {
    if (uiState.needCryptoWallet) OneShotLaunchedEffect(Unit) {
        onNeedCryptoWallet()
    }
    LifecycleResumeEffect(onResume) {
        onResume()
        onPauseOrDispose { }
    }
    Box(modifier = modifier)
}

@Composable
private fun Error(
    modifier: Modifier = Modifier,
    uiState: MainUiState.Error,
    onExitClick: () -> Unit
) {
    Column(
        modifier = modifier.padding(top = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            modifier = Modifier.size(100.dp),
            imageVector = Icons.Default.ErrorOutline,
            tint = MaterialTheme.colorScheme.error,
            contentDescription = stringResource(id = R.string.error)
        )
        Text(
            modifier = Modifier.padding(top = 40.dp),
            text = stringResource(id = uiState.messageResId),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        ElevatedButton(
            modifier = Modifier.padding(top = 40.dp),
            onClick = onExitClick
        ) {
            Text(text = stringResource(id = R.string.exit))
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun SetupPreview() {
    VoteChainTheme {
        Setup(
            modifier = Modifier.fillMaxSize(),
            uiState = MainUiState.Setup(),
            onNeedSimplePasswordVerify = {},
            onVoteAddClick = {},
            onVoteListItemClick = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun NoSetupPreview() {
    VoteChainTheme {
        NoSetup(
            modifier = Modifier.fillMaxSize(),
            uiState = MainUiState.NoSetup(),
            onResume = {},
            onNeedCryptoWallet = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ErrorPreview() {
    VoteChainTheme {
        Error(
            modifier = Modifier.fillMaxSize(),
            uiState = MainUiState.Error(
                messageResId = R.string.auth_error_message
            ),
            onExitClick = {}
        )
    }
}