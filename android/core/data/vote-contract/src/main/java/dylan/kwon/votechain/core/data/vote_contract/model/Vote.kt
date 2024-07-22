package dylan.kwon.votechain.core.data.vote_contract.model

data class Vote(
    val id: Long,
    val owner: String,
    val title: String,
    val content: String,
    val imageUrl: String,
    val voterCount: Long,
    val isAllowDuplicateVoting: Boolean,
    val createdAt: Long,
    val isClosed: Boolean
)
