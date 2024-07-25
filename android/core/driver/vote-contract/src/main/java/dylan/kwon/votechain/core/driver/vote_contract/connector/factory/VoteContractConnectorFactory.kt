package dylan.kwon.votechain.core.driver.vote_contract.connector.factory

import dylan.kwon.votechain.core.driver.vote_contract.connector.VoteContractConnector

interface VoteContractConnectorFactory {

    fun create(
        rpcUrl: String,
        contractAddress: String
    ): VoteContractConnector

}