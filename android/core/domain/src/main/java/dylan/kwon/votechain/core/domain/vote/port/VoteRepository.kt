package dylan.kwon.votechain.core.domain.vote.port

import dylan.kwon.votechain.core.domain.cryptoWallet.entity.CryptoWallet
import dylan.kwon.votechain.core.domain.vote.entity.Vote
import dylan.kwon.votechain.core.domain.vote.entity.VoteContractInfo
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary

interface VoteRepository {

    suspend fun updateContractInfo(info: VoteContractInfo)

    suspend fun getVote(
        id: Long, cryptoWallet: CryptoWallet
    ): Vote

    suspend fun getVoteSummaries(id: Long?): List<VoteSummary>

}