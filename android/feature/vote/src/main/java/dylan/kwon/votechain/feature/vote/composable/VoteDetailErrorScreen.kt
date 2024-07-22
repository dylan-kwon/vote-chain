package dylan.kwon.votechain.feature.vote.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dylan.kwon.votechain.core.ui.design_system.theme.VoteChainTheme
import dylan.kwon.votechain.feature.vote.R
import dylan.kwon.votechain.feature.vote.detail.VoteDetailScreen
import dylan.kwon.votechain.feature.vote.detail.VoteDetailUiState

@Composable
internal fun VoteDetailScreen.Error(
    modifier: Modifier = Modifier,
    uiState: VoteDetailUiState.Error,
    onRetryClick: () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        ElevatedButton(
            onClick = onRetryClick
        ) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    VoteChainTheme {
        VoteDetailScreen.Error(
            uiState = VoteDetailUiState.Error(),
            onRetryClick = {}
        )
    }
}