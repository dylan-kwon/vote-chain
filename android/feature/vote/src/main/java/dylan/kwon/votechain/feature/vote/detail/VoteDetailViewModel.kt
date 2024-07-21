@file:OptIn(ExperimentalCoroutinesApi::class)

package dylan.kwon.votechain.feature.vote.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dylan.kwon.votechain.core.architecture.mvi.MviViewModel
import dylan.kwon.votechain.core.domain.vote.entity.Vote
import dylan.kwon.votechain.core.domain.vote.usecase.GetVoteUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoteDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getVoteUseCase: GetVoteUseCase
) : MviViewModel<VoteDetailUiState>(
    initialUiState = VoteDetailUiState.Loading()
) {

    val id = savedStateHandle.getStateFlow<Long?>(
        "id", null
    )

    private val voteLoading = Channel<Long>()
    private val vote = id.toVoteLoadingFlow()

    init {
        vote.launchIn(viewModelScope)
        voteLoading()
    }

    fun voteLoading() {
        val currentId = id.value ?: return

        viewModelScope.launch {
            setLoading()
            voteLoading.send(currentId)
        }
    }

    fun StateFlow<Long?>.toVoteLoadingFlow() = filterNotNull()
        .mapLatest {
            getVoteUseCase(26)
        }
        .onEach {
            onVoteLoadingResult(it)
        }

    private fun onVoteLoadingResult(result: Result<Vote>) {
        result
            .onSuccess { vote ->
                setLoaded(vote)
            }
            .onFailure {
                setError()
            }
    }


    private fun setLoading() {
        setState {
            VoteDetailUiState.Loading()
        }
    }

    private fun setLoaded(vote: Vote) {
        setState {
            VoteDetailUiState.Loaded(
                title = vote.title
            )
        }
    }

    private fun setError() {
        setState {
            VoteDetailUiState.Error()
        }
    }

}