package dylan.kwon.votechain.feature.vote.screen.add

import dylan.kwon.votechain.core.domain.vote.entity.DomainVoteForm
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class AddVoteUiState(
    val voteForm: VoteForm = VoteForm(),
    val isProgressbarVisible: Boolean = false,
    val isSubmitButtonEnabled: Boolean = false,
    val toastMessage: String? = null,
    val isCreated: Boolean = false,
) {
    data class VoteForm(
        val title: String = "",
        val imageUri: String? = null,
        val content: String = "",
        val ballotItems: ImmutableList<String> = persistentListOf("", ""),
        val isAllowDuplicateVoting: Boolean = false,
    ) {

        val isAddBallotItemButtonVisible: Boolean
            get() = ballotItems.size in DomainVoteForm.BALLOT_ITEMS_MINIMUM_SIZE until DomainVoteForm.BALLOT_ITEMS_MAXIMUM_SIZE

        enum class Type {
            TITLE,
            IMAGE,
            CONTENT,
            BALLOT_ITEM,
            ADD_BALLOT_ITEM_BUTTON,
            DUPLICATE_VOTING_CHECKBOX,
            SUBMIT_BUTTON,
        }
    }
}