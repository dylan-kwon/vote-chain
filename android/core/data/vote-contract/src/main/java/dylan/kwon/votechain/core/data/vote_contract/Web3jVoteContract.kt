package dylan.kwon.votechain.core.data.vote_contract

import dylan.kwon.votechain.core.data.vote_contract.connector.VoteContractConnector
import dylan.kwon.votechain.core.data.vote_contract.connector.factory.VoteContractConnectorFactory
import dylan.kwon.votechain.core.data.vote_contract.mapper.toBallotItem
import dylan.kwon.votechain.core.data.vote_contract.mapper.toVote
import dylan.kwon.votechain.core.data.vote_contract.mapper.toVoter
import dylan.kwon.votechain.core.data.vote_contract.model.BallotItem
import dylan.kwon.votechain.core.data.vote_contract.model.Vote
import dylan.kwon.votechain.core.data.vote_contract.model.Voter
import javax.inject.Inject
import javax.inject.Singleton
import dylan.kwon.votechain.core.data.vote_contract.VoteContractImpl.BallotItem as Web3jBallotItem

@Singleton
class Web3jVoteContract @Inject constructor(
    private val connectorFactory: VoteContractConnectorFactory
) : VoteContract {

    private lateinit var connector: VoteContractConnector

    override suspend fun init(rpcUrl: String, contractAddress: String) {
        connector = connectorFactory.create(rpcUrl, contractAddress)
    }

    override suspend fun getVote(id: Long, privateKey: String): Vote? {
        return connector.connection(privateKey) { impl ->
            val vote = impl.votes(id.toBigInteger()).send().toVote()
            vote.takeIf { it.id > 0 }
        }
    }

    override suspend fun getVoter(id: Long, privateKey: String): Voter? {
        return connector.connection(privateKey) { impl ->
            val voter = impl.getVoter(id.toBigInteger()).send().toVoter()
            voter.takeIf { it.votings.isNotEmpty() }
        }
    }

    override suspend fun getBallotItems(id: Long, privateKey: String): List<BallotItem>? {
        return connector.connection(privateKey) { impl ->
            val ballotItems = impl.getVoteBallotItems(id.toBigInteger()).send().map { data ->
                (data as Web3jBallotItem).toBallotItem()
            }
            ballotItems.takeIf { it.isNotEmpty() }
        }
    }
}