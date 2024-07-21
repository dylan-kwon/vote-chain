package dylan.kwon.votechain.core.data.vote_contract

import dylan.kwon.votechain.core.data.vote_contract.VoteContractImpl.BallotItem
import dylan.kwon.votechain.core.data.vote_contract.model.Vote
import java.math.BigInteger

interface VoteContract {

    suspend fun init(
        rpcUrl: String,
        contractAddress: String
    )

    suspend fun getVote(id: BigInteger, privateKey: String): Vote?

    suspend fun getBallotItems(id: BigInteger, privateKey: String): List<BallotItem>?

}