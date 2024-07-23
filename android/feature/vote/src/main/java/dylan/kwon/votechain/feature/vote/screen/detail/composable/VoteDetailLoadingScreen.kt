@file:OptIn(ExperimentalLayoutApi::class, ExperimentalLayoutApi::class)

package dylan.kwon.votechain.feature.vote.screen.detail.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import dylan.kwon.votechain.core.ui.design_system.theme.VoteChainTheme
import dylan.kwon.votechain.feature.vote.screen.detail.Placeholder
import dylan.kwon.votechain.feature.vote.screen.detail.VoteDetailScreen
import dylan.kwon.votechain.feature.vote.screen.detail.VoteDetailUiState

@Composable
internal fun VoteDetailScreen.Loading(
    modifier: Modifier = Modifier,
    uiState: VoteDetailUiState.Loading
) {
    Column(
        modifier = modifier.shimmer(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Placeholder(
            modifier = Modifier
                .size(80.dp, 24.dp)
                .align(Alignment.End)
        )
        Placeholder(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 1f)
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Placeholder(
                modifier = Modifier.size(80.dp, 40.dp)
            )
            Placeholder(
                modifier = Modifier.size(80.dp, 40.dp)
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(3) {
                Placeholder(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(4) {
                Placeholder(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    VoteChainTheme {
        VoteDetailScreen.Loading(
            uiState = VoteDetailUiState.Loading()
        )
    }
}
