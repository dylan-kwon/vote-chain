package dylan.kwon.votechain.core.domain.vote.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletRepository
import dylan.kwon.votechain.core.domain.vote.port.VoteRepository
import javax.inject.Inject

class CloseVoteUseCase @Inject constructor(
    private val voteRepository: VoteRepository,
    private val cryptoWalletRepository: CryptoWalletRepository
) : UseCase<Long, Unit>() {

    override suspend fun onInvoke(input: Long) {
        voteRepository.closeVote(
            id = input,
            cryptoWallet = cryptoWalletRepository.getSavedCryptoWallet()
        )
    }

}