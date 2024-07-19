package dylan.kwon.votechain.feature.vote.list

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dylan.kwon.votechain.core.architecture.mvi.MviViewModel
import dylan.kwon.votechain.core.domain.vote.usecase.GetVoteSummariesUseCase
import dylan.kwon.votechain.feature.vote.list.mapper.VoteListUiMapper
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
    private val getVoteSummariesUseCase: GetVoteSummariesUseCase
) : MviViewModel<VoteListUiState>(
    initialUiState = VoteListUiState()
), VoteListUiMapper by uiMapper {

    private val search = Channel<String>()
    val voteList = search.toVoteList()

    init {
        search()
    }

    private fun Channel<String>.toVoteList() = receiveAsFlow()
        .distinctUntilChanged()
        .flatMapLatest { searchKeyword ->
            // Firestore Like Query not supported.
            getVoteSummariesUseCase(Unit).filterBy(searchKeyword)
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

}