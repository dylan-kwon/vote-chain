package dylan.kwon.votechain.core.domain.vote.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletPort
import dylan.kwon.votechain.core.domain.vote.port.VotePort
import javax.inject.Inject

class VotingUseCase @Inject constructor(
    private val votePort: VotePort,
    private val cryptoWalletPort: CryptoWalletPort,
) : UseCase<VotingUseCase.Request, Unit>() {

    data class Request(
        val voteId: Long,
        val ballotItemsIds: List<Int>
    )

    override suspend fun onInvoke(input: Request) = votePort.voting(
        id = input.voteId,
        ids = input.ballotItemsIds,
        cryptoWallet = cryptoWalletPort.getSavedCryptoWallet()
    )

}