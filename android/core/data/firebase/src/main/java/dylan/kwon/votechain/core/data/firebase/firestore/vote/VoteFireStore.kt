package dylan.kwon.votechain.core.data.firebase.firestore.vote

import androidx.paging.PagingSource
import dylan.kwon.votechain.core.data.firebase.firestore.vote.model.VoteDocument

interface VoteFireStore {

    fun createVoteListPagingSource(): PagingSource<Long, VoteDocument>

}