package dylan.kwon.votechain.core.domain.vote.entity

data class BallotItem(
    val id: Int,
    val name: String,
    val count: Long,
    val isVoted: Boolean
)