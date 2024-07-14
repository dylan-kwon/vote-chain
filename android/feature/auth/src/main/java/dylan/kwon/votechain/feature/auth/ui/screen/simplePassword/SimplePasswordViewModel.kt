package dylan.kwon.votechain.feature.auth.ui.screen.simplePassword

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dylan.kwon.votechain.core.architecture.mvi.MviViewModel
import dylan.kwon.votechain.core.domain.auth.usecase.SubmitSimplePasswordUseCase
import dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.uiMapper.SimplePasswordUiMapper
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SimplePasswordViewModel @Inject constructor(
    private val uiMapper: SimplePasswordUiMapper,
    private val submitSimplePasswordUseCase: SubmitSimplePasswordUseCase,
) : MviViewModel<SimplePasswordUiState>(
    initialUiState = SimplePasswordUiState()
), SimplePasswordUiMapper by uiMapper {

    init {
        uiState.toSubmitPasswordFlow().launchIn(viewModelScope)
    }

    fun StateFlow<SimplePasswordUiState>.toSubmitPasswordFlow(): Flow<Boolean> {
        return distinctUntilChangedBy {
            it.inputPassword
        }.filter {
            it.isInputCompleted
        }.map {
            it.inputPassword.toSimplePassword()
        }.mapLatest {
            submitSimplePasswordUseCase(it).getOrDefault(false)
        }.onEach(::onSubmitResult)
    }

    fun inputPassword(password: Int) {
        if (uiState.value.isInputCompleted) {
            return
        }
        setState {
            copy(
                inputPassword = this.inputPassword.toPersistentList()
                    .add(password.toString()),
            )
        }
    }

    fun deletePassword() {
        if (!uiState.value.hasInputPassword) {
            return
        }
        setState {
            copy(
                inputPassword = inputPassword.toPersistentList()
                    .dropLast(1)
                    .toImmutableList()
            )
        }
    }

    fun clearInputPassword() {
        setState {
            copy(
                inputPassword = persistentListOf()
            )
        }
        showErrorMessage()
    }

    private fun onSubmitResult(result: Boolean) {
        when (result) {
            true -> showSuccessMessage()
            else -> clearInputPassword()
        }
        setResult(result)
    }

    private fun setResult(result: Boolean) {
        setState {
            copy(
                result = result
            )
        }
    }

    fun showDefaultMessage() = setMessage(
        SimplePasswordUiState.Message.Default
    )

    fun showSuccessMessage() = setMessage(
        SimplePasswordUiState.Message.Success
    )

    fun showErrorMessage() = setMessage(
        SimplePasswordUiState.Message.Error
    )

    private fun setMessage(message: SimplePasswordUiState.Message) {
        setState {
            copy(message = message)
        }
    }

}
