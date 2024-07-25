@file:Suppress("UNCHECKED_CAST")

package dylan.kwon.voetchain.core.adapter.vote

import dylan.kwon.voetchain.core.adapter.vote.mapper.toCreateVoteData
import dylan.kwon.voetchain.core.adapter.vote.mapper.toCredential
import dylan.kwon.voetchain.core.adapter.vote.mapper.toDomain
import dylan.kwon.votechain.core.coroutine.jvm.dispatcher.DefaultDispatcherProvider
import dylan.kwon.votechain.core.driver.firebase.firestore.vote.VoteFireStore
import dylan.kwon.votechain.core.driver.vote_contract.VoteContract
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.CryptoWallet
import dylan.kwon.votechain.core.domain.vote.entity.Vote
import dylan.kwon.votechain.core.domain.vote.entity.VoteContractInfo
import dylan.kwon.votechain.core.domain.vote.entity.VoteForm
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary
import dylan.kwon.votechain.core.domain.vote.port.VotePort
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import dylan.kwon.votechain.core.driver.vote_contract.model.BallotItem as VoteContractBallotItem
import dylan.kwon.votechain.core.driver.vote_contract.model.Vote as VoteContractVote
import dylan.kwon.votechain.core.driver.vote_contract.model.Voter as VoteContractVoter

@Singleton
class VoteAdapter @Inject constructor(
    private val dispatcherProvider: DefaultDispatcherProvider,
    private val voteContract: VoteContract,
    private val voteFireStore: VoteFireStore,
) : VotePort {

    override suspend fun updateContractInfo(info: VoteContractInfo) {
        voteContract.init(info.rpcUrl, info.contractAddress)
    }

    override suspend fun getVote(
        id: Long,
        cryptoWallet: CryptoWallet,
    ): Vote {
        return withContext(dispatcherProvider.io) {

            // Await 3-Requests.
            val results = awaitAll(

                // 1. Get Vote Data
                async {
                    voteContract.getVote(
                        id = id,
                        credential = cryptoWallet.toCredential()
                    )
                },

                // 2. Get Ballot Item Data of Vote
                async {
                    voteContract.getBallotItems(
                        id = id,
                        credential = cryptoWallet.toCredential()
                    )
                },

                // 3. Get Voter Data
                async {
                    voteContract.getVoter(
                        id = id,
                        credential = cryptoWallet.toCredential()
                    )
                },
            )

            // Vote Null-Check
            val vote = results[0] as VoteContractVote?
            if (vote == null) {
                throw NullPointerException()
            }

            // BallotItems Null-Check
            val ballotItems = results[1] as List<VoteContractBallotItem>?
            if (ballotItems == null) {
                throw NullPointerException()
            }

            // If the caller has not yet voted on the poll, it may be null.
            val voter = results[2] as VoteContractVoter?

            val isOwner = cryptoWallet.address == vote.owner

            vote.toDomain(
                isOwner = isOwner,
                ballotItems = ballotItems,
                voting = voter?.votings?.toSet() ?: setOf()
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

    override suspend fun voting(id: Long, ids: List<Int>, cryptoWallet: CryptoWallet) {
        withContext(dispatcherProvider.io) {
            voteContract.voting(id, ids, cryptoWallet.toCredential())
        }
    }

    override suspend fun closeVote(id: Long, cryptoWallet: CryptoWallet) {
        withContext(dispatcherProvider.io) {
            voteContract.closeVote(id, cryptoWallet.toCredential())
        }
    }

    override suspend fun createVote(voteForm: VoteForm, cryptoWallet: CryptoWallet) {
        withContext(dispatcherProvider.io) {
            voteContract.createVote(
                createVoteData = voteForm.toCreateVoteData(),
                credential = cryptoWallet.toCredential()
            )
        }
    }
}
