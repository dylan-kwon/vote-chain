package dylan.kwon.votechain.core.architecture.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn

open class MviViewModel<UiState>(
    initialUiState: UiState,
    intentCapacity: Int = Channel.UNLIMITED
) : ViewModel() {

    private val _intents: Channel<(UiState) -> UiState> = Channel(intentCapacity)
    protected val intents: ReceiveChannel<(UiState) -> UiState> = _intents

    protected val reducer = intents.receiveAsFlow()
        .runningFold(initialUiState) { uiState, intent ->
            intent(uiState)
        }

    open val uiState = reducer.stateIn(
        scope = viewModelScope,
        initialValue = initialUiState,
        started = SharingStarted.Lazily
    )

    protected fun setState(intent: UiState.() -> UiState) =
        _intents.trySend(intent)

}