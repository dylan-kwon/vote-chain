package dylan.kwon.voetchain.core.data.bundle.vote

import dylan.kwon.voetchain.core.data.bundle.vote.mapper.toVoteSummary
import dylan.kwon.votechain.core.data.firebase.firestore.vote.VoteFireStore
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary
import dylan.kwon.votechain.core.domain.vote.part.VoteRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultVoteRepository @Inject constructor(
    private val voteFireStore: VoteFireStore
) : VoteRepository {

    override suspend fun getVoteSummaries(id: Long): List<VoteSummary> =
        voteFireStore.getVoteDocuments(id)
            .filter {
                it.id != null
            }
            .map {
                it.toVoteSummary()
            }
}
