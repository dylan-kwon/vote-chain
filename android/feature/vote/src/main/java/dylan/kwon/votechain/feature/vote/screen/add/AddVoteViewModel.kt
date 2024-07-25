@file:OptIn(ExperimentalCoroutinesApi::class)

package dylan.kwon.votechain.feature.vote.screen.add

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dylan.kwon.votechain.core.architecture.mvi.MviViewModel
import dylan.kwon.votechain.core.coroutine.jvm.dispatcher.DefaultDispatcherProvider
import dylan.kwon.votechain.core.domain.vote.usecase.CreateVoteUseCase
import dylan.kwon.votechain.core.domain.vote.usecase.ValidateVoteFormUseCase
import dylan.kwon.votechain.feature.vote.screen.add.uiMapper.AddVoteUiMapper
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AddVoteViewModel @Inject constructor(
    uiMapper: AddVoteUiMapper,
    private val dispatcherProvider: DefaultDispatcherProvider,
    private val validateVoteFormUseCase: ValidateVoteFormUseCase,
    private val createVoteUseCase: CreateVoteUseCase
) : MviViewModel<AddVoteUiState>(
    initialUiState = AddVoteUiState()
), AddVoteUiMapper by uiMapper {

    private val validateVoteFormFlow = uiState.toValidateVoteFormFlow()

    private val createVote = Channel<AddVoteUiState.VoteForm>()
    private val createVoteFlow = createVote.toCreateFlow()

    init {
        validateVoteFormFlow.launchIn(viewModelScope)
        createVoteFlow.launchIn(viewModelScope)
    }

    private fun StateFlow<AddVoteUiState>.toValidateVoteFormFlow() = uiState.map {
        it.voteForm
    }.mapLatest { voteForm ->
        validateVoteFormUseCase(voteForm.toDomain())
    }.flowOn(dispatcherProvider.io)
        .onEach { result ->
            onVoteFormValidationResult(result)
        }

    private fun onVoteFormValidationResult(result: Result<Boolean>) {
        setState {
            copy(
                isSubmitButtonEnabled = result.getOrDefault(false)
            )
        }
    }

    fun updateImageUri(imageUri: String) {
        setState {
            copy(
                voteForm = voteForm.copy(
                    imageUri = imageUri
                )
            )
        }
    }

    fun updateTitle(title: String) {
        setState {
            copy(
                voteForm = voteForm.copy(
                    title = title
                )
            )
        }
    }

    fun updateContent(content: String) {
        setState {
            copy(
                voteForm = voteForm.copy(
                    content = content
                )
            )
        }
    }

    fun addBallotItem() {
        setState {
            copy(
                voteForm = voteForm.copy(
                    ballotItems = voteForm.ballotItems.toMutableList()
                        .apply { add("") }
                        .toImmutableList()
                )
            )
        }
    }

    fun removeBallotItem(index: Int) {
        setState {
            copy(
                voteForm = voteForm.copy(
                    ballotItems = voteForm.ballotItems.toMutableList()
                        .apply {
                            removeAt(index)
                        }
                        .toImmutableList()
                )
            )
        }
    }

    fun updateBallotItem(index: Int, value: String) {
        setState {
            copy(
                voteForm = voteForm.copy(
                    ballotItems = voteForm.ballotItems.toMutableList()
                        .apply {
                            set(index, value)
                        }
                        .toImmutableList()
                )
            )
        }
    }

    fun updateDuplicateVotingAllow(isAllow: Boolean) {
        setState {
            copy(
                voteForm = voteForm.copy(
                    isAllowDuplicateVoting = isAllow
                )
            )
        }
    }

    fun create() {
        createVote.trySend(uiState.value.voteForm)
    }

    private fun Channel<AddVoteUiState.VoteForm>.toCreateFlow() = consumeAsFlow()
        .onEach {
            onNewVoteCreationStarted()
        }
        .map {
            createVoteUseCase(it.toDomain())
        }
        .onEach {
            onNewVoteCreationResult(it)
        }

    private fun onNewVoteCreationStarted() {
        setState {
            copy(
                isProgressbarVisible = true
            )
        }
    }

    private fun onNewVoteCreationResult(result: Result<Unit>) {
        result
            .onSuccess {
                onNewVoteCreationSuccess()
            }
            .onFailure {
                onNewVoteCreationFailure(it)
            }
        setState {
            copy(
                isProgressbarVisible = false
            )
        }
    }

    private fun onNewVoteCreationSuccess() {
        setState {
            copy(
                isCreated = true
            )
        }
    }

    private fun onNewVoteCreationFailure(e: Throwable) {
        e.message?.let { message ->
            setState {
                copy(
                    toastMessage = message
                )
            }
        }
        e.printStackTrace()
    }

    fun consumeToastMessage() {
        setState {
            copy(
                toastMessage = null,
            )
        }
    }
}