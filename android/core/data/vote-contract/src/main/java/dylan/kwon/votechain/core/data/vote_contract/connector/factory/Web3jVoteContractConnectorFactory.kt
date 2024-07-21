package dylan.kwon.votechain.core.data.vote_contract.connector.factory

import dylan.kwon.votechain.core.data.vote_contract.connector.VoteContractConnector
import dylan.kwon.votechain.core.data.vote_contract.connector.Web3jVoteContractConnector
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Web3jVoteContractConnectorFactory @Inject constructor() : VoteContractConnectorFactory {

    override fun create(rpcUrl: String, contractAddress: String): VoteContractConnector =
        Web3jVoteContractConnector(
            rpcUrl = rpcUrl,
            contractAddress = contractAddress
        )
}