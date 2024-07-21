package dylan.kwon.votechain.feature.vote.list.paging

import androidx.paging.PagingSource
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary
import dylan.kwon.votechain.core.domain.vote.usecase.GetVoteSummariesUseCase
import java.math.BigInteger
import javax.inject.Inject

class DefaultVoteSummaryPagingSourceFactory @Inject constructor(

    private val getVoteSummariesUseCase: GetVoteSummariesUseCase

) : VoteSummaryPagingSourceFactory {

    override fun create(): PagingSource<Long, VoteSummary> =
        VoteSummaryPagingSource(getVoteSummariesUseCase)

}