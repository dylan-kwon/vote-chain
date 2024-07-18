package dylan.kwon.votechain.core.data.firebase.firestore.vote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import dylan.kwon.votechain.core.data.firebase.firestore.vote.model.VoteDocument
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class VoteListPagingSource @Inject constructor() : PagingSource<Long, VoteDocument>() {

    private val collectionRef = Firebase.firestore.collection("Votes")

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
        return anchorPage.data.firstOrNull()?.id
    }
}