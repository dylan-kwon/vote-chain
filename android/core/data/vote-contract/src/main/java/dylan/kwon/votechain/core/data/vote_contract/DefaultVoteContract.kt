package dylan.kwon.votechain.core.data.vote_contract

import dylan.kwon.votechain.core.data.vote_contract.VoteContractImpl.BallotItem
import dylan.kwon.votechain.core.data.vote_contract.connector.VoteContractConnector
import dylan.kwon.votechain.core.data.vote_contract.connector.factory.VoteContractConnectorFactory
import dylan.kwon.votechain.core.data.vote_contract.mapper.toVote
import dylan.kwon.votechain.core.data.vote_contract.model.Vote
import java.math.BigInteger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultVoteContract @Inject constructor(
    private val connectorFactory: VoteContractConnectorFactory
) : VoteContract {

    private lateinit var connector: VoteContractConnector

    override suspend fun init(rpcUrl: String, contractAddress: String) {
        connector = connectorFactory.create(rpcUrl, contractAddress)
    }

    override suspend fun getVote(id: BigInteger, privateKey: String): Vote? {
        return connector.connection(privateKey) {
            it.votes(id).send().toVote()
        }
    }

    override suspend fun getBallotItems(id: BigInteger, privateKey: String): List<BallotItem>? {
        return connector.connection(privateKey) {
            it.getVoteBallotItems(id).send().map { data ->
                data as BallotItem
            }
        }
    }
}