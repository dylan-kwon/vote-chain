package dylan.kwon.votechain.core.data.vote_contract.model


data class Voter(
    val id: String,
    val votings: List<Long>
)