@file:OptIn(FlowPreview::class)

package dylan.kwon.votechain.core.data.vote_contract

import dylan.kwon.votechain.core.data.vote_contract.connector.VoteContractConnector
import dylan.kwon.votechain.core.data.vote_contract.connector.factory.VoteContractConnectorFactory
import dylan.kwon.votechain.core.data.vote_contract.mapper.mapBigInteger
import dylan.kwon.votechain.core.data.vote_contract.mapper.toBallotItem
import dylan.kwon.votechain.core.data.vote_contract.mapper.toVote
import dylan.kwon.votechain.core.data.vote_contract.mapper.toVoter
import dylan.kwon.votechain.core.data.vote_contract.model.BallotItem
import dylan.kwon.votechain.core.data.vote_contract.model.CreateVoteData
import dylan.kwon.votechain.core.data.vote_contract.model.Credential
import dylan.kwon.votechain.core.data.vote_contract.model.Vote
import dylan.kwon.votechain.core.data.vote_contract.model.Voter
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.timeout
import kotlinx.coroutines.reactive.asFlow
import org.web3j.protocol.core.DefaultBlockParameter
import org.web3j.protocol.core.DefaultBlockParameterName
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import dylan.kwon.votechain.core.data.vote_contract.VoteContractImpl.BallotItem as Web3jBallotItem

@Singleton
class Web3jVoteContract @Inject constructor(
    private val connectorFactory: VoteContractConnectorFactory
) : VoteContract {

    companion object {
        const val TIMEOUT = 10
        const val CHAINLINK_TIMEOUT = 5
    }

    private lateinit var connector: VoteContractConnector

    override suspend fun init(rpcUrl: String, contractAddress: String) {
        connector = connectorFactory.create(rpcUrl, contractAddress)
    }

    override suspend fun getVote(id: Long, credential: Credential): Vote? {
        return connector.connection(credential.private) { contract ->
            val vote = contract.votes(id.toBigInteger()).send().toVote()
            vote.takeIf { it.id > 0 }
        }
    }

    override suspend fun getVoter(id: Long, credential: Credential): Voter? {
        return connector.connection(credential.private) { contract ->
            val voter = contract.getVoter(id.toBigInteger()).send().toVoter()
            voter.takeIf { it.votings.isNotEmpty() }
        }
    }

    override suspend fun getBallotItems(id: Long, credential: Credential): List<BallotItem>? {
        return connector.connection(credential.private) { contract ->
            val ballotItems = contract.getVoteBallotItems(id.toBigInteger()).send().map { data ->
                (data as Web3jBallotItem).toBallotItem()
            }
            ballotItems.takeIf { it.isNotEmpty() }
        }
    }

    override suspend fun voting(id: Long, ids: List<Int>, credential: Credential) {
        connector.connection(credential.private) { contract ->

            // Request Voting
            val transactionReceipt = contract.voting(
                id.toBigInteger(),
                ids.mapBigInteger()
            ).send()

            // Await Response
            contract.votingEventFlowable(
                DefaultBlockParameter.valueOf(transactionReceipt.blockNumber),
                DefaultBlockParameterName.LATEST,
            ).asFlow()
                .filter {
                    it.voteId == id.toBigInteger()
                }
                .filter {
                    it.voter == credential.address
                }
                .timeout(TIMEOUT.seconds)
                .first()
        }
    }

    override suspend fun closeVote(id: Long, credential: Credential) {
        connector.connection(credential.private) { contract ->

            // Request Close Vote
            val transactionReceipt = contract.closeVote(id.toBigInteger()).send()

            // Await Response
            contract.closeVoteEventFlowable(
                DefaultBlockParameter.valueOf(transactionReceipt.blockNumber),
                DefaultBlockParameterName.LATEST,
            ).asFlow()
                .filter {
                    it.voteId == id.toBigInteger()
                }
                .timeout(TIMEOUT.seconds)
                .first()
        }
    }

    override suspend fun createVote(createVoteData: CreateVoteData, credential: Credential) {
        connector.connection(credential.private) { contract ->

            // Request Create Vote
            val transactionReceipt = contract.createVote(
                createVoteData.title,
                createVoteData.content,
                createVoteData.imageUrl ?: "",
                createVoteData.isAllowDuplicateVoting,
                createVoteData.ballotItems,
            ).send()

            // Await Create Response
            val createVoteResponse = contract.createVoteEventFlowable(
                DefaultBlockParameter.valueOf(transactionReceipt.blockNumber),
                DefaultBlockParameterName.LATEST,
            ).asFlow()
                .filter {
                    it.owner == credential.address
                }
                .timeout(TIMEOUT.seconds)
                .first()

            // Await ChainLink Response
            contract.chainLinkResponseEventFlowable(
                DefaultBlockParameter.valueOf(transactionReceipt.blockNumber),
                DefaultBlockParameterName.LATEST,
            ).asFlow()
                .filter {
                    it.requestId.contentEquals(createVoteResponse.chainLinkRequestId)
                }
                .onEach {
                    if (!it.err.isNullOrEmpty()) {
                        throw Exception(it.err)
                    }
                }
                .timeout(CHAINLINK_TIMEOUT.minutes)
                .first()
        }
    }
}