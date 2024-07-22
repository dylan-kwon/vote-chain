package dylan.kwon.votechain.core.domain.vote.entity

data class Vote(
    val id: Long,

    val title: String,

    val content: String,

    val imageUrl: String?,

    val voterCount: Long,

    val isOwner: Boolean,

    val isVoted: Boolean,

    val isClosed: Boolean,

    val isAllowDuplicateVoting: Boolean,

    val ballotItems: List<BallotItem>,

    val createdAt: Long,
)