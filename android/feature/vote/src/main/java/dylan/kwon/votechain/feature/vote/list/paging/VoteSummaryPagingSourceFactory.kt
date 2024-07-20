package dylan.kwon.votechain.feature.vote.list.paging

import androidx.paging.PagingSource
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary

interface VoteSummaryPagingSourceFactory {

    fun create(): PagingSource<Long, VoteSummary>

}