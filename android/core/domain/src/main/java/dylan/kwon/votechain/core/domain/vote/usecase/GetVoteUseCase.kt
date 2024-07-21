package dylan.kwon.votechain.core.domain.vote.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletRepository
import dylan.kwon.votechain.core.domain.vote.entity.Vote
import dylan.kwon.votechain.core.domain.vote.port.VoteRepository
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetVoteUseCase @Inject constructor(
    private val voteRepository: VoteRepository,
    private val cryptoWalletRepository: CryptoWalletRepository,
) : UseCase<Long, Vote>() {

    override suspend fun onInvoke(input: Long): Vote = coroutineScope {
        val cryptoWallet = cryptoWalletRepository.getSavedCryptoWallet()
        voteRepository.getVote(input, cryptoWallet)
    }

}