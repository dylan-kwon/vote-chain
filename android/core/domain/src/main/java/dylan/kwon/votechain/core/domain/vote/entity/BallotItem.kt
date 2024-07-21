package dylan.kwon.votechain.core.domain.vote.entity

import java.math.BigInteger

data class BallotItem(
    val name: String,
    val count: BigInteger
)