package dylan.kwon.votechain.ui.screen.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dylan.kwon.votechain.R
import dylan.kwon.votechain.core.architecture.mvi.MviViewModel
import dylan.kwon.votechain.core.domain.setup.entity.AppSetupResult
import dylan.kwon.votechain.core.domain.setup.usecase.AppSetupUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appSetupUseCase: AppSetupUseCase
) : MviViewModel<MainUiState>(
    initialUiState = MainUiState.NoSetup()
) {

    private val _appSetup = Channel<Unit>()
    private val _appSetupFlow = _appSetup.toSetupFlow()

    init {
        _appSetupFlow.launchIn(viewModelScope)
    }

    fun setup() {
        _appSetup.trySend(Unit)
    }

    private fun Channel<Unit>.toSetupFlow() = consumeAsFlow()
        .mapLatest {
            appSetupUseCase(Unit)
        }
        .onEach {
            onSetupResult(it)
        }

    fun onSetupResult(result: Result<AppSetupResult>) {
        when (result.getOrNull()) {
            AppSetupResult.NeedCryptoWallet -> onNeedCryptoWallet()
            AppSetupResult.Completed -> onSetupCompleted()
            else -> onAuthError()
        }
    }

    private fun onNeedCryptoWallet() {
        setState {
            MainUiState.NoSetup(
                isAuth = true,
                hasCryptoWallet = false
            )
        }
    }

    private fun onSetupCompleted() {
        setState {
            MainUiState.Setup()
        }
    }

    private fun onAuthError() {
        setState {
            MainUiState.Error(
                messageResId = R.string.auth_error_message
            )
        }
    }

    fun verifiedSimplePassword() {
        if (uiState.value !is MainUiState.Setup) {
            return
        }
        setState {
            MainUiState.Setup(
                isVerifiedSimplePassword = true
            )
        }
    }

}