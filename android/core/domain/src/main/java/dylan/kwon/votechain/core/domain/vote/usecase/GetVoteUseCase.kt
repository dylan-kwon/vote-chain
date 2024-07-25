package dylan.kwon.votechain.core.domain.vote.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletPort
import dylan.kwon.votechain.core.domain.vote.entity.Vote
import dylan.kwon.votechain.core.domain.vote.port.VotePort
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetVoteUseCase @Inject constructor(
    private val votePort: VotePort,
    private val cryptoWalletPort: CryptoWalletPort,
) : UseCase<Long, Vote>() {

    override suspend fun onInvoke(input: Long): Vote = coroutineScope {
        val cryptoWallet = cryptoWalletPort.getSavedCryptoWallet()
        votePort.getVote(input, cryptoWallet)
    }

}