package dylan.kwon.votechain.core.domain.vote.entity

data class Vote(
    val id: Long,
//    val id: ULong,

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
        const val BALLOT_ITEMS_MAXIMUM_SIZE = 5
    }

    val hasTitle: Boolean
        get() = title.isNotBlank()

    val hasContent: Boolean
        get() = content.isNotBlank()

    val hasImage: Boolean
        get() = !imageUrl.isNullOrEmpty()

    val hasBallotItems: Boolean
        get() = ballotItems.isNotEmpty()

    fun isValid(isIncludeId: Boolean = true): Boolean {
        if (isIncludeId) {
            if (id <= 0) {
                return false
            }
        }
        if (!hasTitle) {
            return false
        }
        if (!hasContent) {
            return false
        }
        if (!isBallotItemsValid(isIncludeId)) {
            return false
        }
        return true
    }

    fun isBallotItemsValid(isIncludeId: Boolean = true): Boolean {
        if (!hasBallotItems) {
            return false
        }
        if (ballotItems.size > BALLOT_ITEMS_MAXIMUM_SIZE) {
            return false
        }
        if (ballotItems.any { !it.isValid(isIncludeId) }) {
            return false
        }
        return true
    }

}