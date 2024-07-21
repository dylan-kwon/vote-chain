package dylan.kwon.votechain.core.data.vote_contract.connector

import dylan.kwon.votechain.core.data.vote_contract.VoteContractImpl

interface VoteContractConnector {

    suspend fun <T> connection(
        privateKey: String,
        run: suspend (VoteContractImpl) -> T
    ): T?

}