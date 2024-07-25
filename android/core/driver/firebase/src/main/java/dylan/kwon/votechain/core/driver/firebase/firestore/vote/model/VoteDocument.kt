package dylan.kwon.votechain.core.driver.firebase.firestore.vote.model

data class VoteDocument(
    val id: Long? = null,

    val title: String? = null,

    val content: String? = null,

    val imageUrl: String? = null,

    @field:JvmField
    val isClosed: Boolean? = null,

    val createdAt: Long? = null,
) {

    object Field {
        const val ID = "id"
        const val TITLE = "title"
        const val CONTENT = "content"
        const val IMAGE_URL = "imageUrl"
        const val IS_CLOSED = "isClosed"
        const val CREATED_AT = "createdAt"
    }

}