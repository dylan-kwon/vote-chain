package dylan.kwon.votechain.core.domain.vote.entity

import java.math.BigInteger

data class Voter(
    val id: String,
    val votings: List<BigInteger>
)