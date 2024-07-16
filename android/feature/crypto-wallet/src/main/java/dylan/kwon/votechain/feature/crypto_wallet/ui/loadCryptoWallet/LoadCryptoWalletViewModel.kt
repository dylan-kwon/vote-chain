package dylan.kwon.votechain.feature.crypto_wallet.ui.loadCryptoWallet

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dylan.kwon.votechain.core.architecture.mvi.MviViewModel
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.Mnemonic
import dylan.kwon.votechain.core.domain.cryptoWallet.usecase.LoadCryptoWalletUseCase
import dylan.kwon.votechain.core.domain.cryptoWallet.usecase.ValidateMnemonicUseCase
import dylan.kwon.votechain.feature.crypto_wallet.ui.loadCryptoWallet.mapper.LoadCryptoWalletUiMapper
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoadCryptoWalletViewModel @Inject constructor(
    private val uiMapper: LoadCryptoWalletUiMapper,
    private val validateMnemonicUseCase: ValidateMnemonicUseCase,
    private val loadCryptoWalletUseCase: LoadCryptoWalletUseCase,
) : MviViewModel<LoadCryptoWalletUiState>(
    initialUiState = LoadCryptoWalletUiState()
), LoadCryptoWalletUiMapper by uiMapper {

    private val _loadCryptoWallet = Channel<Mnemonic>()

    init {
        uiState.toValidMnemonicFlow()
            .launchIn(viewModelScope)

        _loadCryptoWallet.toLoadCryptoWalletFlow()
            .launchIn(viewModelScope)
    }

    private fun StateFlow<LoadCryptoWalletUiState>.toValidMnemonicFlow() = map {
        it.mnemonic
    }.filter {
        it.isNotEmpty()
    }.distinctUntilChanged()
        .mapLatest {
            validateMnemonicUseCase(it.toMnemonic())
        }
        .onEach {
            onMnemonicValidationResult(it)
        }

    private fun Channel<Mnemonic>.toLoadCryptoWalletFlow() = consumeAsFlow()
        .onEach {
            onCryptoWalletLoadingStarted()
        }
        .mapLatest {
            loadCryptoWalletUseCase(it)
        }
        .onEach {
            onCryptoWalletLoadingResult(it)
        }

    private fun onMnemonicValidationResult(result: Result<Boolean>) {
        val isSuccess = result.getOrDefault(false)
        when (isSuccess) {
            true -> setMnemonicState(
                LoadCryptoWalletUiState.MnemonicState.Valid
            )

            else -> setMnemonicState(
                LoadCryptoWalletUiState.MnemonicState.Invalid
            )
        }
    }

    private fun setMnemonicState(state: LoadCryptoWalletUiState.MnemonicState) {
        setState {
            copy(
                mnemonicState = state
            )
        }
    }

    fun updateMnemonic(mnemonic: String) {
        setState {
            copy(
                mnemonic = mnemonic
            )
        }
    }

    fun loadCryptoWallet() {
        _loadCryptoWallet.trySend(
            uiState.value.mnemonic.toMnemonic()
        )
    }

    private fun onCryptoWalletLoadingStarted() {
        setCryptoWalletState(
            LoadCryptoWalletUiState.CryptoWalletState.Loading
        )
    }

    private fun onCryptoWalletLoadingResult(result: Result<Unit>) {
        result
            .onSuccess {
                onCryptoWalletLoaded()
            }
            .onFailure {
                onCryptoWalletLoadingError()
            }
    }

    fun onCryptoWalletLoaded() {
        setCryptoWalletState(
            LoadCryptoWalletUiState.CryptoWalletState.Loaded
        )
    }

    fun onCryptoWalletLoadingError() {
        setCryptoWalletState(
            LoadCryptoWalletUiState.CryptoWalletState.Default
        )
    }

    private fun setCryptoWalletState(state: LoadCryptoWalletUiState.CryptoWalletState) {
        setState {
            copy(
                cryptoWalletState = state
            )
        }
    }
}
