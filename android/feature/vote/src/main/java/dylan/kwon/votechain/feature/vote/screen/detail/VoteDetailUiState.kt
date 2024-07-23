package dylan.kwon.votechain.feature.vote.screen.detail

import kotlinx.collections.immutable.ImmutableList

sealed interface VoteDetailUiState {

    val title: String

    val isLoading: Boolean
        get() = this is Loading

    val isLoaded: Boolean
        get() = this is Loaded

    val isMoreMenuShowing: Boolean

    data class Loading(
        override val title: String = "",
        override val isMoreMenuShowing: Boolean = false
    ) : VoteDetailUiState

    data class Loaded(
        val vote: Vote,
        val canVoting: Boolean,
        val canMultipleChoice: Boolean,
        val isProgressShowing: Boolean = false,
        val toastMessage: String? = null,
        override val isMoreMenuShowing: Boolean,
    ) : VoteDetailUiState {

        data class Vote(
            val title: String,
            val imageUrl: String?,
            val content: String,
            val voteStatus: Status,
            val voterCount: String,
            val ballotItems: ImmutableList<BallotItem>,
            val createdAgo: String
        ) {
            enum class Status {
                IN_PROGRESS,
                CLOSED,
            }
        }

        data class BallotItem(
            val id: Int,
            val name: String,
            val votingCount: Long,
            val isVoted: Boolean
        )

        override val title: String
            get() = vote.title

        val isSubmitButtonEnabled: Boolean
            get() = vote.ballotItems.find { it.isVoted } != null
    }

    data class Error(
        override val title: String = "",
        override val isMoreMenuShowing: Boolean = false
    ) : VoteDetailUiState

}