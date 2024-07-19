package dylan.kwon.votechain.core.data.firebase.firestore.vote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import dylan.kwon.votechain.core.data.firebase.firestore.vote.model.VoteDocument
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class VoteListPagingSource @Inject constructor(
    fireStore: FirebaseFirestore,
) : PagingSource<Long, VoteDocument>() {

    private val collectionRef = fireStore.collection("Votes")

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, VoteDocument> {
        try {
            val key = params.key ?: Long.MAX_VALUE
            val result = requestVotes(key)
            return LoadResult.Page(
                data = result,
                prevKey = null,
                nextKey = result.lastOrNull()?.id
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    private suspend fun requestVotes(key: Long) = collectionRef
        .whereLessThan(
            VoteDocument.Field.ID,
            key
        )
        .orderBy(
            VoteDocument.Field.ID,
            Query.Direction.DESCENDING
        )
        .limit(30)
        .get()
        .await()
        .map {
            it.toObject<VoteDocument>()
        }

    override fun getRefreshKey(state: PagingState<Long, VoteDocument>): Long? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        val firstId = anchorPage.data.firstOrNull()?.id ?: return null
        return firstId + 1
    }
}