package dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem

data class VoteListItemUiState(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: Long,
    val status: Status
) {
    enum class Status {
        IN_PROGRESS,
        CLOSED,
    }
}