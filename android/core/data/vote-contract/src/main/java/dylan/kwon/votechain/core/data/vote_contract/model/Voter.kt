package dylan.kwon.votechain.core.data.vote_contract.model

import java.math.BigInteger


data class Voter(
    val id: String,
    val votings: List<BigInteger>
)