package dylan.kwon.votechain.core.domain.vote.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletRepository
import dylan.kwon.votechain.core.domain.vote.port.VoteRepository
import javax.inject.Inject

class VotingUseCase @Inject constructor(
    private val voteRepository: VoteRepository,
    private val cryptoWalletRepository: CryptoWalletRepository,
) : UseCase<VotingUseCase.Request, Unit>() {

    data class Request(
        val voteId: Long,
        val ballotItemsIds: List<Int>
    )

    override suspend fun onInvoke(input: Request) = voteRepository.voting(
        id = input.voteId,
        ids = input.ballotItemsIds,
        cryptoWallet = cryptoWalletRepository.getSavedCryptoWallet()
    )

}