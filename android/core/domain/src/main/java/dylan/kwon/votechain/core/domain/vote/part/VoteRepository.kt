package dylan.kwon.votechain.core.domain.vote.part

import androidx.paging.PagingData
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary
import kotlinx.coroutines.flow.Flow

interface VoteRepository {

    fun getVoteSummaries(): Flow<PagingData<VoteSummary>>

}