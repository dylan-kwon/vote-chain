package dylan.kwon.votechain.core.data.vote_contract

import dylan.kwon.votechain.core.data.vote_contract.model.BallotItem
import dylan.kwon.votechain.core.data.vote_contract.model.Vote
import dylan.kwon.votechain.core.data.vote_contract.model.Voter

interface VoteContract {

    suspend fun init(
        rpcUrl: String,
        contractAddress: String
    )

    suspend fun getVote(id: Long, privateKey: String): Vote?

    suspend fun getVoter(id: Long, privateKey: String): Voter?

    suspend fun getBallotItems(id: Long, privateKey: String): List<BallotItem>?

}