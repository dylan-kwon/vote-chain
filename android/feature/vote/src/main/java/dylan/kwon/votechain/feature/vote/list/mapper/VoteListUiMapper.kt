package dylan.kwon.votechain.feature.vote.list.mapper

import androidx.paging.PagingData
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary
import dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem.VoteListItemUiState
import kotlinx.coroutines.flow.Flow

interface VoteListUiMapper {

    fun Flow<PagingData<VoteSummary>>.filterBy(keyword: String): Flow<PagingData<VoteSummary>>

    fun PagingData<VoteSummary>.toVoteListUiState(): PagingData<VoteListItemUiState>

    fun VoteSummary.toVoteListUiState(): VoteListItemUiState
}