package dylan.kwon.votechain.core.domain.vote.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary
import dylan.kwon.votechain.core.domain.vote.port.VotePort
import javax.inject.Inject

class GetVoteSummariesUseCase @Inject constructor(
    private val votePort: VotePort
) : UseCase<Long?, List<VoteSummary>>() {

    override suspend fun onInvoke(input: Long?): List<VoteSummary> =
        votePort.getVoteSummaries(input)

}