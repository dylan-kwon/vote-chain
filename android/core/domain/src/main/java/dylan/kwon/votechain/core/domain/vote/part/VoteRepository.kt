package dylan.kwon.votechain.core.domain.vote.part

import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary

interface VoteRepository {

    suspend fun getVoteSummaries(id: Long): List<VoteSummary>

}