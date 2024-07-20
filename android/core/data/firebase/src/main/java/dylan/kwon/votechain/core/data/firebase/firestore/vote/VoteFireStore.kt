package dylan.kwon.votechain.core.data.firebase.firestore.vote

import com.google.firebase.firestore.Query
import dylan.kwon.votechain.core.data.firebase.firestore.vote.model.VoteDocument

interface VoteFireStore {

    companion object {
        const val DEFAULT_PAGE_SIZE = 30L
    }

    suspend fun getVoteDocuments(
        id: Long,
        pageSize: Long = DEFAULT_PAGE_SIZE,
        order: Query.Direction = Query.Direction.DESCENDING
    ): List<VoteDocument>

}