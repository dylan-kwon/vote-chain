package dylan.kwon.votechain.feature.vote.screen.add.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dylan.kwon.votechain.feature.vote.screen.add.AddVoteUiState
import kotlinx.collections.immutable.persistentListOf

class AddVoteUiStatePreviewParameterProvider : PreviewParameterProvider<AddVoteUiState> {

    private val empty = AddVoteUiState()

    private val fill = AddVoteUiState(
        voteForm = AddVoteUiState.VoteForm(
            title = "New Vote",
            imageUri = "https://img.hankyung.com/photo/202401/03.35225885.1.jpg",
            content = "Hello World",
            ballotItems = persistentListOf(
                "Ballot Item 1",
                "Ballot Item 1"
            ),
            isAllowDuplicateVoting = true
        ),
    )

    private val loading = fill.copy(
        isProgressbarVisible = true
    )

    override val values: Sequence<AddVoteUiState> = sequenceOf(
        empty, fill, loading
    )

}