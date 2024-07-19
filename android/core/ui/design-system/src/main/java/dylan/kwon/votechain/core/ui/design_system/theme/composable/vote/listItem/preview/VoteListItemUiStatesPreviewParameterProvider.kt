package dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem.VoteListItemUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class VoteListItemUiStatesPreviewParameterProvider :
    PreviewParameterProvider<Flow<PagingData<VoteListItemUiState>>> {

    private val loading = flowOf(
        PagingData.from(
            data = listOf<VoteListItemUiState>(),
            sourceLoadStates = LoadStates(
                refresh = LoadState.Loading,
                append = LoadState.Loading,
                prepend = LoadState.Loading,
            )
        )
    )

    private val loaded = flowOf(
        PagingData.from(
            data = listOf(
                VoteListItemUiState(
                    id = 1,
                    title = "Vote-1",
                    content = "content",
                    createdAt = System.currentTimeMillis(),
                    status = VoteListItemUiState.Status.IN_PROGRESS
                ),
                VoteListItemUiState(
                    id = 2,
                    title = "Vote-2",
                    content = "content",
                    createdAt = System.currentTimeMillis(),
                    status = VoteListItemUiState.Status.CLOSED
                ),
            ),
            sourceLoadStates = LoadStates(
                refresh = LoadState.NotLoading(true),
                append = LoadState.NotLoading(true),
                prepend = LoadState.NotLoading(true),
            )
        )
    )

    private val error = flowOf(
        PagingData.from(
            data = listOf<VoteListItemUiState>(),
            sourceLoadStates = LoadStates(
                refresh = LoadState.Error(Exception()),
                append = LoadState.Error(Exception()),
                prepend = LoadState.Error(Exception()),
            )
        )
    )


    override val values: Sequence<Flow<PagingData<VoteListItemUiState>>> =
        sequenceOf(loading, loaded, error)

}