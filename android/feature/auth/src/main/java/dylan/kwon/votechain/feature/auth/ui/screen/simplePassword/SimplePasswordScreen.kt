package dylan.kwon.votechain.feature.auth.ui.screen.simplePassword

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dylan.kwon.votechain.core.ui.design_system.theme.VoteChainTheme
import dylan.kwon.votechain.core.ui.design_system.theme.composable.messageCard.MessageCard
import dylan.kwon.votechain.feature.auth.ui.composable.numPad.NumPad
import dylan.kwon.votechain.feature.auth.ui.composable.passwordDisplay.PasswordDisplay

@Composable
internal fun SimplePasswordRoute(
    modifier: Modifier = Modifier,
    viewModel: SimplePasswordViewModel = hiltViewModel(),
    onResult: (SimplePasswordNavigationResult) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val result = uiState.result
    if (result == true) LaunchedEffect(result) {
        onResult(SimplePasswordNavigationResult(result))
    }

    SimplePasswordScreen(
        modifier = modifier,
        uiState = uiState,
        onClickNumPad = viewModel::inputPassword,
        onDeleteClick = viewModel::deletePassword
    )
}

@Composable
internal fun SimplePasswordScreen(
    modifier: Modifier = Modifier,
    uiState: SimplePasswordUiState,
    onClickNumPad: (number: Int) -> Unit,
    onDeleteClick: () -> Unit
) {
    Scaffold(
        modifier = modifier
    ) { paddingValues ->

        val modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)

        val configuration = LocalConfiguration.current
        when (configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                PortraitScreen(
                    modifier = modifier,
                    uiState = uiState,
                    onNumberClick = onClickNumPad,
                    onDeleteClick = onDeleteClick
                )
            }

            else -> {
                LandscapeScreen(
                    modifier = modifier,
                    uiState = uiState,
                    onNumberClick = onClickNumPad,
                    onDeleteClick = onDeleteClick
                )
            }
        }
    }
}

@Composable
private fun PortraitScreen(
    modifier: Modifier = Modifier,
    uiState: SimplePasswordUiState,
    onNumberClick: (number: Int) -> Unit,
    onDeleteClick: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(
                56.dp, Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MessageCard(
                message = stringResource(id = uiState.message.stringResId),
                state = uiState.message.state
            )

            PasswordDisplay(
                modifier = Modifier.fillMaxWidth(),
                maximumSize = uiState.passwordLength,
                inputtedSize = uiState.inputSize
            )
        }

        NumPad(
            modifier = Modifier.fillMaxWidth(),
            onNumberClick = onNumberClick,
            onDeleteClick = onDeleteClick,
        )
    }
}

@Composable
private fun LandscapeScreen(
    modifier: Modifier = Modifier,
    uiState: SimplePasswordUiState,
    onNumberClick: (number: Int) -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(
                56.dp, Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MessageCard(
                message = stringResource(id = uiState.message.stringResId),
                state = uiState.message.state
            )

            PasswordDisplay(
                modifier = Modifier.fillMaxWidth(),
                maximumSize = uiState.passwordLength,
                inputtedSize = uiState.inputSize
            )
        }

        NumPad(
            modifier = Modifier
                .padding(end = 24.dp)
                .fillMaxHeight(),
            onNumberClick = onNumberClick,
            onDeleteClick = onDeleteClick,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun SimplePasswordScreenLandscapePreview() {
    VoteChainTheme {
        PortraitScreen(
            uiState = SimplePasswordUiState(),
            onNumberClick = {},
            onDeleteClick = {}
        )
    }
}

@Composable
@Preview(showBackground = true, device = Devices.AUTOMOTIVE_1024p)
private fun SimplePasswordScreenPortraitPreview() {
    VoteChainTheme {
        LandscapeScreen(
            uiState = SimplePasswordUiState(),
            onNumberClick = {},
            onDeleteClick = {}
        )
    }
}
