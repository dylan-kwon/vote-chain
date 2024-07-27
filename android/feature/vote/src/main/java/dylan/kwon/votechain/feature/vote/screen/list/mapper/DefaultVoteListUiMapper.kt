package dylan.kwon.votechain.feature.vote.screen.list.mapper

import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary
import dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem.VoteListItemUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultVoteListUiMapper @Inject constructor() : VoteListUiMapper {

    override fun Flow<PagingData<VoteSummary>>.filterBy(keyword: String) = map { pagingData ->
        pagingData.filter {
            it.content.contains(keyword)
        }
    }

    override fun PagingData<VoteSummary>.toVoteListUiState() = map { voteSummary ->
        voteSummary.toVoteListUiState()
    }

    override fun VoteSummary.toVoteListUiState() = VoteListItemUiState(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt * 1000,
        status = when (isClosed) {
            true -> VoteListItemUiState.Status.CLOSED
            else -> VoteListItemUiState.Status.IN_PROGRESS
        }
    )

}