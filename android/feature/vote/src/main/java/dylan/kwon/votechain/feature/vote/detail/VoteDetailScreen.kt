@file:Suppress("UnusedReceiverParameter")
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package dylan.kwon.votechain.feature.vote.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dylan.kwon.votechain.core.ui.design_system.theme.VoteChainTheme
import dylan.kwon.votechain.feature.vote.composable.Error
import dylan.kwon.votechain.feature.vote.composable.Loaded
import dylan.kwon.votechain.feature.vote.composable.Loading
import dylan.kwon.votechain.feature.vote.composable.TopBar
import dylan.kwon.votechain.feature.vote.composable.preview.VoteDetailLoadedUiStatePreviewParameterProvider
import dylan.kwon.votechain.feature.vote.detail.VoteDetailUiState.Loaded.BallotItem as BallotItemUiState

object VoteDetailScreen

@Composable
internal fun VoteDetailRoute(
    viewModel: VoteDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    VoteDetailScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onRetryClick = viewModel::retry,
        onVoteCloseClick = viewModel::closeVote,
        onBallotItemCheckedChange = viewModel::updateBallotItemChecked,
        onSubmitClick = viewModel::voting,
        onConsumedToastMessage = viewModel::consumeToastMessage
    )
}

@Composable
internal fun VoteDetailScreen(
    uiState: VoteDetailUiState,
    onBackClick: () -> Unit,
    onRetryClick: () -> Unit,
    onVoteCloseClick: () -> Unit,
    onBallotItemCheckedChange: (BallotItemUiState) -> Unit,
    onSubmitClick: () -> Unit,
    onConsumedToastMessage: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(
            scrollBehavior.nestedScrollConnection
        ),
        topBar = {
            VoteDetailScreen.TopBar(
                scrollBehavior = scrollBehavior,
                title = uiState.title,
                isOwner = uiState.isMoreMenuShowing,
                isLoading = uiState.isLoading,
                onBackClick = onBackClick,
                onVoteCloseClick = onVoteCloseClick
            )
        }
    ) { paddingValues ->
        val modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp, vertical = 24.dp)

        AnimatedContent(
            targetState = uiState,
            label = "vote-detail-screen",
            contentKey = {
                it::class
            },
            transitionSpec = {
                when (initialState.isLoaded) {
                    true -> EnterTransition.None togetherWith ExitTransition.Companion.None
                    else -> fadeIn() togetherWith fadeOut()
                }
            }
        ) { target ->
            when (target) {
                is VoteDetailUiState.Loading -> VoteDetailScreen.Loading(
                    modifier = modifier,
                    uiState = target
                )

                is VoteDetailUiState.Loaded -> VoteDetailScreen.Loaded(
                    modifier = modifier,
                    uiState = target,
                    onBallotItemCheckedChange = onBallotItemCheckedChange,
                    onSubmitClick = onSubmitClick,
                    onConsumedToastMessage = onConsumedToastMessage,
                )

                is VoteDetailUiState.Error -> VoteDetailScreen.Error(
                    modifier = modifier,
                    uiState = target,
                    onRetryClick = onRetryClick
                )
            }
        }
    }
}

@Composable
internal fun VoteDetailScreen.Placeholder(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colorScheme.surfaceDim)
    )
}

@Composable
@Preview(showBackground = true)
internal fun LoadingPreview() {
    VoteChainTheme {
        VoteDetailScreen(
            uiState = VoteDetailUiState.Loading(),
            onBackClick = {},
            onRetryClick = {},
            onVoteCloseClick = {},
            onBallotItemCheckedChange = {},
            onSubmitClick = {},
            onConsumedToastMessage = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
internal fun LoadedPreview(
    @PreviewParameter(VoteDetailLoadedUiStatePreviewParameterProvider::class)
    uiState: VoteDetailUiState.Loaded
) {
    VoteChainTheme {
        VoteDetailScreen(
            uiState = uiState,
            onBackClick = {},
            onRetryClick = {},
            onVoteCloseClick = {},
            onBallotItemCheckedChange = {},
            onSubmitClick = {},
            onConsumedToastMessage = {}
        )
    }
}

@Composable
@Preview(showBackground = true)
internal fun ErrorPreview() {
    VoteChainTheme {
        VoteDetailScreen(
            uiState = VoteDetailUiState.Error(),
            onBackClick = {},
            onRetryClick = {},
            onVoteCloseClick = {},
            onBallotItemCheckedChange = {},
            onSubmitClick = {},
            onConsumedToastMessage = {}
        )
    }
}