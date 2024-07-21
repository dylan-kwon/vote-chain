package dylan.kwon.votechain.feature.vote.detail

sealed interface VoteDetailUiState {

    val title: String

    data class Loading(
        override val title: String = ""
    ) : VoteDetailUiState

    data class Loaded(
        override val title: String,
    ) : VoteDetailUiState

    data class Error(
        override val title: String = ""
    ) : VoteDetailUiState

}