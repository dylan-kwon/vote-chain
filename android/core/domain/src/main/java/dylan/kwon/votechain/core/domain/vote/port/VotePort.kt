package dylan.kwon.votechain.core.domain.vote.port

import dylan.kwon.votechain.core.domain.cryptoWallet.entity.CryptoWallet
import dylan.kwon.votechain.core.domain.vote.entity.Vote
import dylan.kwon.votechain.core.domain.vote.entity.VoteContractInfo
import dylan.kwon.votechain.core.domain.vote.entity.VoteForm
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary

interface VotePort {

    suspend fun updateContractInfo(info: VoteContractInfo)

    suspend fun getVote(id: Long, cryptoWallet: CryptoWallet): Vote

    suspend fun getVoteSummaries(id: Long?): List<VoteSummary>

    suspend fun voting(id: Long, ids: List<Int>, cryptoWallet: CryptoWallet)

    suspend fun closeVote(id: Long, cryptoWallet: CryptoWallet)

    suspend fun createVote(voteForm: VoteForm, cryptoWallet: CryptoWallet)
}