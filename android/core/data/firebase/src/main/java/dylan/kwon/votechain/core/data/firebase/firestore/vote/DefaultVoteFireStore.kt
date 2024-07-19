package dylan.kwon.votechain.core.data.firebase.firestore.vote

import androidx.paging.PagingSource
import com.google.firebase.firestore.FirebaseFirestore
import dylan.kwon.votechain.core.data.firebase.firestore.vote.model.VoteDocument
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultVoteFireStore @Inject constructor(
    private val firestore: FirebaseFirestore
) : VoteFireStore {

    override fun createVoteListPagingSource(): PagingSource<Long, VoteDocument> =
        VoteListPagingSource(firestore)

}