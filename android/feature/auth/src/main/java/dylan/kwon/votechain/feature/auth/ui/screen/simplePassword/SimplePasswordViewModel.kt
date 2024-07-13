package dylan.kwon.votechain.feature.auth.ui.screen.simplePassword

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dylan.kwon.votechain.core.architecture.mvi.MviViewModel
import dylan.kwon.votechain.core.domain.use_case.auth.GetInitializedSimplePasswordUseCase
import dylan.kwon.votechain.core.domain.use_case.auth.ValidateSimplePasswordUseCase
import dylan.kwon.votechain.feature.auth.R
import dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.uiMapper.SimplePasswordUiMapper
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SimplePasswordViewModel @Inject constructor(
    private val uiMapper: SimplePasswordUiMapper,
    private val validateSimplePasswordUseCase: ValidateSimplePasswordUseCase,
    getInitializedSimplePasswordUseCase: GetInitializedSimplePasswordUseCase
) : MviViewModel<SimplePasswordUiState>(
    initialUiState = SimplePasswordUiState()
), SimplePasswordUiMapper by uiMapper {

    private val isPasswordInitialized = getInitializedSimplePasswordUseCase(Unit)
        .mapLatest {
            it.getOrDefault(false)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = false
        )

    init {
        onPasswordChanged()
        onPasswordInitializeChanged()
        onInputCompleted()
    }

    fun inputPassword(password: Int) {
        if (uiState.value.isInputComplete) {
            return
        }
        viewModelScope.launch {
            setState {
                copy(
                    password = this.password.toPersistentList()
                        .add(password.toString())
                        .toImmutableList(),
                )
            }
        }
    }

    fun deletePassword() {
        if (!uiState.value.hasPassword) {
            return
        }
        viewModelScope.launch {
            setState {
                copy(
                    password = password.toPersistentList()
                        .dropLast(1)
                        .toImmutableList()
                )
            }
        }
    }

    private fun onPasswordChanged() {
        uiState
            .filter {
                it.isInputComplete
            }
            .map {
                it.password
            }
            .distinctUntilChanged()
            .map {
                it.toSimplePassword()
            }
            .map {
                validateSimplePasswordUseCase(it)
                    .getOrDefault(false)
            }
            .onEach { result ->
                setState {
                    copy(isPasswordValid = result)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun onPasswordInitializeChanged() {
        isPasswordInitialized
            .map {
                when (it) {
                    true -> null
                    else -> R.string.password_set_message
                }
            }
            .onEach {
                setState {
                    copy(
                        messageResId = it
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun onInputCompleted() {
        uiState
            .filter {
                it.isInputComplete
            }
            .onEach {

            }
            .launchIn(viewModelScope)
    }
}
