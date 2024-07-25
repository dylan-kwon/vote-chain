package dylan.kwon.votechain.core.domain.vote.entity

typealias DomainVoteForm = VoteForm

data class VoteForm(

    val title: String,

    val content: String,

    val imageUri: String?,

    val isAllowDuplicateVoting: Boolean,

    val ballotItems: List<String>

) {

    companion object {
        const val BALLOT_ITEMS_MINIMUM_SIZE = Vote.BALLOT_ITEMS_MINIMUM_SIZE
        const val BALLOT_ITEMS_MAXIMUM_SIZE = Vote.BALLOT_ITEMS_MAXIMUM_SIZE
        val BALLOT_ITEMS_SIZE_RANGE = Vote.BALLOT_ITEMS_SIZE_RANGE
    }

    val hasTitle: Boolean
        get() = title.isNotBlank()

    val hasContent: Boolean
        get() = content.isNotBlank()

    val hasImage: Boolean
        get() = !imageUri.isNullOrEmpty()

    val hasBallotItems: Boolean
        get() = ballotItems.isNotEmpty()

    fun isValid(): Boolean {
        if (!hasTitle) {
            return false
        }
        if (!hasContent) {
            return false
        }
        if (!isBallotItemsValid()) {
            return false
        }
        return true
    }

    fun isBallotItemsValid(): Boolean {
        if (!hasBallotItems) {
            return false
        }
        if (ballotItems.size !in BALLOT_ITEMS_SIZE_RANGE) {
            return false
        }
        if (ballotItems.any { it.isBlank() }) {
            return false
        }
        return true
    }

}