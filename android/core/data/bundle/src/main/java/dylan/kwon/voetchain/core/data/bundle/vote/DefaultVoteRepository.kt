@file:Suppress("UNCHECKED_CAST")

package dylan.kwon.voetchain.core.data.bundle.vote

import dylan.kwon.voetchain.core.data.bundle.vote.mapper.toDomain
import dylan.kwon.votechain.core.coroutine.jvm.dispatcher.DefaultDispatcherProvider
import dylan.kwon.votechain.core.data.firebase.firestore.vote.VoteFireStore
import dylan.kwon.votechain.core.data.vote_contract.VoteContract
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.CryptoWallet
import dylan.kwon.votechain.core.domain.vote.entity.Vote
import dylan.kwon.votechain.core.domain.vote.entity.VoteContractInfo
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary
import dylan.kwon.votechain.core.domain.vote.port.VoteRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import dylan.kwon.votechain.core.data.vote_contract.VoteContractImpl.BallotItem as ContractBallotItem
import dylan.kwon.votechain.core.data.vote_contract.model.Vote as ContractVote

@Singleton
class DefaultVoteRepository @Inject constructor(
    private val dispatcherProvider: DefaultDispatcherProvider,
    private val voteContract: VoteContract,
    private val voteFireStore: VoteFireStore,
) : VoteRepository {

    override suspend fun updateContractInfo(info: VoteContractInfo) {
        voteContract.init(info.rpcUrl, info.contractAddress)
    }

    override suspend fun getVote(
        id: Long,
        cryptoWallet: CryptoWallet,
    ): Vote {
        return withContext(dispatcherProvider.io) {
            val results = awaitAll(
                async {
                    voteContract.getVote(
                        id = id.toBigInteger(),
                        privateKey = cryptoWallet.privateKey
                    )
                },
                async {
                    voteContract.getBallotItems(
                        id = id.toBigInteger(),
                        privateKey = cryptoWallet.privateKey
                    )
                }
            )
            val vote = results.first() as ContractVote
            val ballotItems = results.last() as List<ContractBallotItem>

            vote.toDomain(
                ballotItems = ballotItems
            )
        }
    }

    override suspend fun getVoteSummaries(id: Long?): List<VoteSummary> {
        return withContext(dispatcherProvider.io) {
            voteFireStore.getVoteDocuments(id)
                .filter {
                    it.id != null
                }
                .map {
                    it.toDomain()
                }
        }
    }
}
