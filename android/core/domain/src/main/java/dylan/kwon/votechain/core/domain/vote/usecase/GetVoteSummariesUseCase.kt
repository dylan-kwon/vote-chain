package dylan.kwon.votechain.core.domain.vote.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary
import dylan.kwon.votechain.core.domain.vote.part.VoteRepository
import javax.inject.Inject

class GetVoteSummariesUseCase @Inject constructor(
    private val voteRepository: VoteRepository
) : UseCase<Long, List<VoteSummary>>() {

    override suspend fun onInvoke(input: Long): List<VoteSummary> =
        voteRepository.getVoteSummaries(input)

}