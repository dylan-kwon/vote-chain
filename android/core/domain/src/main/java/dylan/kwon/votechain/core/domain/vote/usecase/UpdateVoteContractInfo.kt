package dylan.kwon.votechain.core.domain.vote.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.config.port.ConfigPort
import dylan.kwon.votechain.core.domain.vote.entity.toContractInfo
import dylan.kwon.votechain.core.domain.vote.port.VotePort
import javax.inject.Inject

class UpdateVoteContractInfo @Inject constructor(
    private val votePort: VotePort,
    private val configPort: ConfigPort
) : UseCase<Unit, Unit>() {
    override suspend fun onInvoke(input: Unit) {
        votePort.updateContractInfo(
            configPort.getConfig().toContractInfo()
        )
    }
}