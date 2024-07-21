package dylan.kwon.votechain.core.data.vote_contract.model

import java.math.BigInteger

data class Vote(
    val id: BigInteger,
    val owner: String,
    val title: String,
    val content: String,
    val imageUrl: String,
    val isAllowDuplicateVoting: Boolean,
    val createdAt: BigInteger,
    val isClosed: Boolean
)
