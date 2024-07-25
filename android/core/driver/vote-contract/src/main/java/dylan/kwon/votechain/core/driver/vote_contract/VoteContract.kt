package dylan.kwon.votechain.core.driver.vote_contract

import dylan.kwon.votechain.core.driver.vote_contract.model.BallotItem
import dylan.kwon.votechain.core.driver.vote_contract.model.CreateVoteData
import dylan.kwon.votechain.core.driver.vote_contract.model.Credential
import dylan.kwon.votechain.core.driver.vote_contract.model.Vote
import dylan.kwon.votechain.core.driver.vote_contract.model.Voter

interface VoteContract {

    suspend fun init(
        rpcUrl: String,
        contractAddress: String
    )

    suspend fun getVote(id: Long, credential: Credential): Vote?

    suspend fun getVoter(id: Long, credential: Credential): Voter?

    suspend fun getBallotItems(id: Long, credential: Credential): List<BallotItem>?

    suspend fun voting(id: Long, ids: List<Int>, credential: Credential)

    suspend fun closeVote(id: Long, credential: Credential)

    suspend fun createVote(createVoteData: CreateVoteData, credential: Credential)

}