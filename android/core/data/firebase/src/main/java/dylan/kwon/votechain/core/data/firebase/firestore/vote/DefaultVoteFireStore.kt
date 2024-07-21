package dylan.kwon.votechain.core.data.firebase.firestore.vote

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import dylan.kwon.votechain.core.data.firebase.firestore.vote.model.VoteDocument
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultVoteFireStore @Inject constructor(
    firestore: FirebaseFirestore
) : VoteFireStore {

    private val ref by lazy {
        firestore.collection("Votes")
    }

    override suspend fun getVoteDocuments(
        id: Long?,
        pageSize: Long,
        order: Query.Direction,
    ): List<VoteDocument> = ref
        .run {
            when (id) {
                null -> this
                else -> whereLessThan(
                    VoteDocument.Field.ID,
                    id
                )
            }
        }
        .orderBy(
            VoteDocument.Field.ID,
            Query.Direction.DESCENDING
        )
        .limit(pageSize)
        .get()
        .await()
        .map {
            it.toObject<VoteDocument>()
        }
}