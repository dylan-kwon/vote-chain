@file:OptIn(ExperimentalCoroutinesApi::class, ExperimentalCoroutinesApi::class)

package dylan.kwon.votechain.feature.vote.screen.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dylan.kwon.votechain.core.architecture.mvi.MviViewModel
import dylan.kwon.votechain.core.domain.vote.entity.Vote
import dylan.kwon.votechain.core.domain.vote.usecase.CloseVoteUseCase
import dylan.kwon.votechain.core.domain.vote.usecase.GetVoteUseCase
import dylan.kwon.votechain.core.domain.vote.usecase.VotingUseCase
import dylan.kwon.votechain.feature.vote.screen.detail.uiMapper.VoteDetailUiMapper
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoteDetailViewModel @Inject constructor(
    uiMapper: VoteDetailUiMapper,
    private val savedStateHandle: SavedStateHandle,
    private val getVoteUseCase: GetVoteUseCase,
    private val votingUseCase: VotingUseCase,
    private val closeVoteUseCase: CloseVoteUseCase,
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

    private val voting = Channel<VotingUseCase.Request>()
    private val votingFlow = voting.toVotingFlow()

    private val closeVote = Channel<Long>()
    private val closeVoteFlow = closeVote.toCloseVoteFlow()

    private val _isVoteLoadingProgress = MutableStateFlow(false)
    private val isVoteLoadingProgress = _isVoteLoadingProgress.asStateFlow()

    private val _isVotingProgress = MutableStateFlow(false)
    private val isVotingProgress = _isVotingProgress.asStateFlow()

    private val _isCloseVoteProgress = MutableStateFlow(false)
    private val isCloseVoteProgress = _isCloseVoteProgress.asStateFlow()

    init {
        with(viewModelScope) {
            vote.launchIn(this)
            votingFlow.launchIn(this)
            closeVoteFlow.launchIn(this)
            createProgressBarStateFlow().launchIn(this)
        }
        when (currentId) {
            null -> setError()
            else -> loadingVoteData()
        }
    }

    fun retry() {
        setLoading()
        loadingVoteData()
    }

    private fun loadingVoteData() {
        val currentId = currentId ?: return

        viewModelScope.launch {
            voteLoading.send(currentId)
        }
    }

    private fun Channel<Long>.toVoteLoadingFlow() = consumeAsFlow()
        .onEach {
            onVoteLoadingStarted()
        }
        .mapLatest {
            getVoteUseCase(it)
        }
        .onEach {
            onVoteLoadingResult(it)
        }

    private fun onVoteLoadingStarted() {
        _isVoteLoadingProgress.value = true
    }

    private fun onVoteLoadingResult(result: Result<Vote>) {
        result
            .onSuccess { vote ->
                setLoaded(vote)
            }
            .onFailure {
                setError()
            }
        _isVoteLoadingProgress.value = false
    }


    private fun setLoading() {
        setState {
            VoteDetailUiState.Loading()
        }
    }

    private fun setLoaded(vote: Vote) {
        setState {
            VoteDetailUiState.Loaded(
                canVoting = !vote.isVoted && !vote.isClosed,
                isMoreMenuShowing = vote.isOwner && !vote.isClosed,
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

    fun voting() {
        val uiState = uiState.value
        if (uiState !is VoteDetailUiState.Loaded) {
            return
        }

        val currentId = currentId
        if (currentId == null) {
            return
        }

        voting.trySend(
            VotingUseCase.Request(
                voteId = currentId,
                ballotItemsIds = uiState.vote.ballotItems.getVotedIds()
            )
        )
    }

    private fun List<VoteDetailUiState.Loaded.BallotItem>.getVotedIds(): List<Int> {
        return filter {
            it.isVoted
        }.map {
            it.id
        }
    }

    private fun Channel<VotingUseCase.Request>.toVotingFlow() = voting.consumeAsFlow()
        .filter {
            (uiState.value as? VoteDetailUiState.Loaded)?.isProgressShowing == false
        }
        .filter {
            (uiState.value as? VoteDetailUiState.Loaded)?.canVoting == true
        }
        .onEach {
            onVotingStarted()
        }
        .map { ids ->
            votingUseCase(ids)
        }
        .onEach {
            onVotingResult(it)
        }

    private fun onVotingStarted() {
        _isVotingProgress.value = true
    }

    private fun onVotingResult(result: Result<Unit>) {
        result
            .onSuccess {
                onVotingSuccess()
            }.onFailure {
                onVotingFailure(it)
            }
        _isVotingProgress.value = false
    }

    private fun onVotingSuccess() {
        loadingVoteData()
    }

    private fun onVotingFailure(throwable: Throwable) {
        with(throwable) {
            message?.let {
                showToast(it)
            }
            printStackTrace()
        }
    }

    private fun showToast(message: String) {
        setState {
            if (this !is VoteDetailUiState.Loaded) {
                return@setState this
            }
            copy(
                toastMessage = message
            )
        }
    }

    fun consumeToastMessage() {
        setState {
            if (this !is VoteDetailUiState.Loaded) {
                return@setState this
            }
            copy(
                toastMessage = null
            )
        }
    }

    fun createProgressBarStateFlow() =
        combine(
            isVoteLoadingProgress,
            isVotingProgress,
            isCloseVoteProgress
        ) { isVoteLoadingProgress, isVotingProgress, isCloseVoteProgress ->
            isVoteLoadingProgress || isVotingProgress || isCloseVoteProgress
        }.onEach {
            onNewProgressBarState(it)
        }

    private fun onNewProgressBarState(newState: Boolean) {
        when (newState) {
            true -> showProgressBar()
            else -> hideProgressBar()
        }
    }

    private fun showProgressBar() {
        setProgressBarState(true)
    }

    private fun hideProgressBar() {
        setProgressBarState(false)
    }

    private fun setProgressBarState(isShowing: Boolean) {
        setState {
            if (this !is VoteDetailUiState.Loaded) {
                return@setState this
            }
            copy(
                isProgressShowing = isShowing
            )
        }
    }

    fun closeVote() {
        val currentId = currentId
        if (currentId == null) {
            return
        }
        closeVote.trySend(currentId)
    }

    private fun Channel<Long>.toCloseVoteFlow() = consumeAsFlow()
        .filter {
            (uiState.value as? VoteDetailUiState.Loaded)?.isProgressShowing == false
        }
        .onEach {
            onVoteClosingStated()
        }
        .mapLatest { id ->
            closeVoteUseCase(id)
        }
        .onEach {
            onVoteClosingResult(it)
        }

    private fun onVoteClosingStated() {
        _isCloseVoteProgress.value = true
    }

    private fun onVoteClosingResult(result: Result<Unit>) {
        result
            .onSuccess {
                onVoteClosingSuccess()
            }
            .onFailure {
                onVoteClosingFailure(it)
            }
        _isCloseVoteProgress.value = false
    }

    private fun onVoteClosingSuccess() {
        loadingVoteData()
    }

    private fun onVoteClosingFailure(e: Throwable) {
        e.message?.let {
            showToast(it)
        }
    }
}
