package dylan.kwon.votechain.core.domain.vote.usecase

import androidx.paging.PagingData
import dylan.kwon.votechain.core.architecture.clean_architecture.FlowUseCase
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary
import dylan.kwon.votechain.core.domain.vote.part.VoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVoteSummariesUseCase @Inject constructor(
    private val voteRepository: VoteRepository
) : FlowUseCase<Unit, PagingData<VoteSummary>>() {
    override fun onInvoke(input: Unit): Flow<PagingData<VoteSummary>> =
        voteRepository.getVoteSummaries()
}