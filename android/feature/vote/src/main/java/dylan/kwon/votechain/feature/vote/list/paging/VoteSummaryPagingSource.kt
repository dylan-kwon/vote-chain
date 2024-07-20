package dylan.kwon.votechain.feature.vote.list.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary
import dylan.kwon.votechain.core.domain.vote.usecase.GetVoteSummariesUseCase
import javax.inject.Inject

class VoteSummaryPagingSource @Inject constructor(
    private val getVoteSummariesUseCase: GetVoteSummariesUseCase
) : PagingSource<Long, VoteSummary>() {

    override suspend fun load(params: LoadParams<Long>): LoadResult<Long, VoteSummary> {
        try {
            val key = params.key ?: Long.MAX_VALUE
            val result = getVoteSummariesUseCase(key).getOrThrow()

            return LoadResult.Page(
                data = result,
                prevKey = null,
                nextKey = result.lastOrNull()?.id,
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Long, VoteSummary>): Long? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        val firstId = anchorPage.data.firstOrNull()?.id ?: return null
        return firstId + 1
    }
}