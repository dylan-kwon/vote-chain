package dylan.kwon.votechain.core.driver.vote_contract.model


data class Voter(
    val id: String,
    val votings: List<Long>
)