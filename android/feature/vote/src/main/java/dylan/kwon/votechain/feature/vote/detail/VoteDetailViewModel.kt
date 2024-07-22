@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalCoroutinesApi::class)

package dylan.kwon.votechain.feature.vote.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dylan.kwon.votechain.core.architecture.mvi.MviViewModel
import dylan.kwon.votechain.core.domain.vote.entity.Vote
import dylan.kwon.votechain.core.domain.vote.usecase.GetVoteUseCase
import dylan.kwon.votechain.feature.vote.detail.uiMapper.VoteDetailUiMapper
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoteDetailViewModel @Inject constructor(
    uiMapper: VoteDetailUiMapper,
    private val savedStateHandle: SavedStateHandle,
    private val getVoteUseCase: GetVoteUseCase
) : MviViewModel<VoteDetailUiState>(
    initialUiState = VoteDetailUiState.Loading()
), VoteDetailUiMapper by uiMapper {

//    val id = savedStateHandle.getStateFlow<Long?>(
//        "id", null
//    )

    val currentId: Long?
        get() = savedStateHandle["id"]

    private val voteLoading = Channel<Long>()
    private val vote = voteLoading.toVoteLoadingFlow()

    init {
        vote.launchIn(viewModelScope)

        when (currentId) {
            null -> setError()
            else -> voteLoading()
        }
    }

    fun retry() {
        voteLoading()
    }

    private fun voteLoading() {
        val currentId = currentId ?: return

        viewModelScope.launch {
            setLoading()
            voteLoading.send(currentId)
        }
    }

    fun Channel<Long>.toVoteLoadingFlow() = consumeAsFlow()
        .mapLatest {
            // todo:
            getVoteUseCase(1)
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
                canVoting = !vote.isVoted,
                isMoreMenuShowing = vote.isOwner,
                canMultipleChoice = vote.isAllowDuplicateVoting,
                vote = vote.toUiState(),
            )
        }
    }

    private fun setError() {
        setState {
            VoteDetailUiState.Error()
        }
    }

    fun updateBallotItemChecked(ballotItem: VoteDetailUiState.Loaded.BallotItem) = setState {
        // Check Loaded State
        if (this !is VoteDetailUiState.Loaded) {
            return@setState this
        }

        // BallotItems to MutableList
        val newBallotItems = vote.ballotItems.toMutableList()

        // Check Allow Multiple Choice
        val canMultipleChoice = canMultipleChoice
        if (!canMultipleChoice) {
            // Clear Choices
            newBallotItems.clearChoices()
        }

        // Checked Change
        val index = newBallotItems.indexOfFirst { it.id == ballotItem.id }
        newBallotItems[index] = ballotItem.copy(
            isVoted = !ballotItem.isVoted
        )

        // Update
        return@setState copy(
            vote = vote.copy(
                ballotItems = newBallotItems.toImmutableList()
            )
        )
    }

    private fun MutableList<VoteDetailUiState.Loaded.BallotItem>.clearChoices() {
        forEachIndexed { index, newBallotItem ->
            this[index] = newBallotItem.copy(
                isVoted = false
            )
        }
    }

    fun closeVote() {
        // todo:
    }

}