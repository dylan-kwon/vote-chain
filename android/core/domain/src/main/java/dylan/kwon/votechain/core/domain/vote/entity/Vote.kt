package dylan.kwon.votechain.core.domain.vote.entity

typealias DomainVote = Vote

data class Vote(
    val id: Long,

    val title: String,

    val content: String,

    val imageUrl: String?,

    val voterCount: Long,

    val isOwner: Boolean,

    val isVoted: Boolean,

    val isClosed: Boolean,

    val isAllowDuplicateVoting: Boolean,

    val ballotItems: List<BallotItem>,

    val createdAt: Long

) {

    companion object {
        const val BALLOT_ITEMS_MINIMUM_SIZE = 2
        const val BALLOT_ITEMS_MAXIMUM_SIZE = 5

        val BALLOT_ITEMS_SIZE_RANGE = IntRange(
            BALLOT_ITEMS_MINIMUM_SIZE,
            BALLOT_ITEMS_MAXIMUM_SIZE
        )
    }

    val hasTitle: Boolean
        get() = title.isNotBlank()

    val hasContent: Boolean
        get() = content.isNotBlank()

    val hasImage: Boolean
        get() = !imageUrl.isNullOrEmpty()

    val hasBallotItems: Boolean
        get() = ballotItems.isNotEmpty()

    fun isValid(): Boolean {
        if (id <= 0) {
            return false
        }
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
        if (ballotItems.any { !it.isValid() }) {
            return false
        }
        return true
    }

}