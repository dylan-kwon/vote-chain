package dylan.kwon.votechain.core.driver.vote_contract.model

data class CreateVoteData(
    val title: String,
    val content: String,
    val imageUrl: String?,
    val isAllowDuplicateVoting: Boolean,
    val ballotItems: List<String>
)