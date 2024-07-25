package dylan.kwon.votechain.feature.vote.screen.list

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dylan.kwon.votechain.core.architecture.mvi.MviViewModel
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary
import dylan.kwon.votechain.feature.vote.screen.list.mapper.VoteListUiMapper
import dylan.kwon.votechain.feature.vote.screen.list.paging.VoteSummaryPagingSourceFactory
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoteListViewModel @Inject constructor(
    uiMapper: VoteListUiMapper,
    private val voteSummaryPagingSourceFactory: VoteSummaryPagingSourceFactory,
) : MviViewModel<VoteListUiState>(
    initialUiState = VoteListUiState()
), VoteListUiMapper by uiMapper {

    private var currentPagingSource: PagingSource<Long, VoteSummary>? = null

    private val votePager = Pager(
        config = PagingConfig(pageSize = 30, enablePlaceholders = true)
    ) {
        voteSummaryPagingSourceFactory.create().also {
            currentPagingSource = it
        }
    }

    private val search = Channel<String>()
    val voteList = search.toVoteList()

    init {
        search()
    }

    private fun Channel<String>.toVoteList() = receiveAsFlow()
        .distinctUntilChanged()
        .flatMapLatest { searchKeyword ->
            // Firestore Like Query not supported.
            votePager.flow.filterBy(searchKeyword)
        }
        .map { pagingData ->
            pagingData.toVoteListUiState()
        }
        .cachedIn(viewModelScope)

    fun updateSearchKeyword(searchKeyword: String) {
        setState {
            copy(
                searchKeyword = searchKeyword
            )
        }
    }

    fun search() {
        viewModelScope.launch {
            search.send(uiState.value.searchKeyword)
        }
    }

    fun refresh() {
        currentPagingSource?.invalidate()
    }
}
