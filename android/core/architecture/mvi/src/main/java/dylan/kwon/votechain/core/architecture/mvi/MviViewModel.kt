package dylan.kwon.votechain.core.architecture.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn

open class MviViewModel<State>(
    initialUiState: State
) : ViewModel() {

    protected val intents: Channel<(State) -> State> = Channel()

    protected val reducer = intents.receiveAsFlow()
        .runningFold(initialUiState) { state, intent ->
            intent(state)
        }

    open val uiState = reducer.stateIn(
        scope = viewModelScope,
        initialValue = initialUiState,
        started = SharingStarted.Eagerly
    )

    protected suspend fun setState(intent: State.() -> State) {
        intents.send(intent)
    }
}