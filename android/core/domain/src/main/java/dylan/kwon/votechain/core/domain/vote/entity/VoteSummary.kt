package dylan.kwon.votechain.core.domain.vote.entity

data class VoteSummary(
    val id: Long,

    val title: String,

    val content: String,

    val imageUrl: String?,

    val isClosed: Boolean,

    val createdAt: Long,
)