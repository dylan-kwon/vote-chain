package dylan.kwon.votechain.core.domain.vote.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletPort
import dylan.kwon.votechain.core.domain.vote.port.VotePort
import javax.inject.Inject

class CloseVoteUseCase @Inject constructor(
    private val votePort: VotePort,
    private val cryptoWalletPort: CryptoWalletPort
) : UseCase<Long, Unit>() {

    override suspend fun onInvoke(input: Long) {
        votePort.closeVote(
            id = input,
            cryptoWallet = cryptoWalletPort.getSavedCryptoWallet()
        )
    }

}