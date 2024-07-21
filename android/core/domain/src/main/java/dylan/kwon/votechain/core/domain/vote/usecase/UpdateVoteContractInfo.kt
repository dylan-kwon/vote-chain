package dylan.kwon.votechain.core.domain.vote.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.config.port.ConfigRepository
import dylan.kwon.votechain.core.domain.vote.entity.toContractInfo
import dylan.kwon.votechain.core.domain.vote.port.VoteRepository
import javax.inject.Inject

class UpdateVoteContractInfo @Inject constructor(
    private val voteRepository: VoteRepository,
    private val configRepository: ConfigRepository
) : UseCase<Unit, Unit>() {
    override suspend fun onInvoke(input: Unit) {
        voteRepository.updateContractInfo(
            configRepository.getConfig().toContractInfo()
        )
    }
}