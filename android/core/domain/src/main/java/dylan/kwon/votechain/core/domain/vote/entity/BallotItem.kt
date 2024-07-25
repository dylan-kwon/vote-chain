package dylan.kwon.votechain.core.domain.vote.entity

data class BallotItem(
    val id: Int,
    val name: String,
    val count: Long,
    val isVoted: Boolean
) {

    val hasName: Boolean
        get() = name.isNotBlank()

    fun isValid(): Boolean {
        if (id <= 0) {
            return false
        }
        if (!hasName) {
            return false
        }
        return true
    }
}