@file:OptIn(ExperimentalCoroutinesApi::class)

package dylan.kwon.voetchain.core.data.bundle.vote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import dylan.kwon.voetchain.core.data.bundle.vote.mapper.toVoteSummary
import dylan.kwon.votechain.core.data.firebase.firestore.vote.VoteFireStore
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary
import dylan.kwon.votechain.core.domain.vote.part.VoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultVoteRepository @Inject constructor(
    private val voteFireStore: VoteFireStore
) : VoteRepository {

    override fun getVoteSummaries(): Flow<PagingData<VoteSummary>> =
        Pager(
            config = PagingConfig(pageSize = 30)
        ) {
            voteFireStore.createVoteListPagingSource()
        }.flow.mapLatest { data ->
            data
                .filter {
                    it.id != null
                }
                .map {
                    it.toVoteSummary()
                }
        }
}
