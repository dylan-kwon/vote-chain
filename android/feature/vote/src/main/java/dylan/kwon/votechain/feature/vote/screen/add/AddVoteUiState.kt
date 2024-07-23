package dylan.kwon.votechain.feature.vote.screen.add

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import dylan.kwon.votechain.core.domain.vote.entity.Vote as DomainVote

data class AddVoteUiState(
    val vote: Vote = Vote(),
    val isProgressbarVisible: Boolean
) {
    data class Vote(
        val title: String = "",
        val imagePath: String? = null,
        val content: String = "",
        val ballotItems: ImmutableList<String> = persistentListOf(),
        val isAllowDuplicateVoting: Boolean = false
    )

    val isAddBallotItemButtonVisible: Boolean
        get() = vote.ballotItems.size <= DomainVote.BALLOT_ITEMS_MAXIMUM_SIZE

    val isCreateButtonEnabled: Boolean
        get() = vote.title.isNotBlank()
                && vote.content.isNotBlank()
                && vote.ballotItems.isNotEmpty()
                && !vote.ballotItems.any { it.isNotBlank() }
}