package dylan.kwon.votechain.feature.vote.screen.detail.composable.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dylan.kwon.votechain.feature.vote.screen.detail.VoteDetailUiState
import kotlinx.collections.immutable.persistentListOf

class VoteDetailLoadedUiStatePreviewParameterProvider :
    PreviewParameterProvider<VoteDetailUiState.Loaded> {

    private val inProgressAndVotable = VoteDetailUiState.Loaded(
        canVoting = true,
        canMultipleChoice = true,
        isMoreMenuShowing = true,
        vote = VoteDetailUiState.Loaded.Vote(
            title = "Vote Title",
            content = "Vote Content",
            imageUrl = "https://img.hankyung.com/photo/202401/03.35225885.1.jpg",
            voteStatus = VoteDetailUiState.Loaded.Vote.Status.IN_PROGRESS,
            ballotItems = persistentListOf(
                VoteDetailUiState.Loaded.BallotItem(id = 1, name = "Item 1", 10, true),
                VoteDetailUiState.Loaded.BallotItem(id = 2, name = "Item 2", 20, false)
            ),
            voterCount = "3,000",
            createdAgo = "3 Days Ago"
        )
    )

    private val inProgressAndNotVotable = inProgressAndVotable.copy(
        canVoting = false
    )

    private val closed = inProgressAndVotable.copy(
        canVoting = false,
        vote = inProgressAndVotable.vote.copy(
            voteStatus = VoteDetailUiState.Loaded.Vote.Status.CLOSED
        )
    )

    private val noImage = inProgressAndVotable.copy(
        vote = inProgressAndVotable.vote.copy(
            imageUrl = null
        )
    )

    override val values: Sequence<VoteDetailUiState.Loaded> =
        sequenceOf(
            inProgressAndVotable,
            inProgressAndNotVotable,
            closed,
            noImage
        )

}