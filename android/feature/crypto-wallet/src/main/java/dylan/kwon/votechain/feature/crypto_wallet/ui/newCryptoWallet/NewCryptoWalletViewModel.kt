package dylan.kwon.votechain.feature.crypto_wallet.ui.newCryptoWallet

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dylan.kwon.votechain.core.architecture.mvi.MviViewModel
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.Mnemonic
import dylan.kwon.votechain.core.domain.cryptoWallet.usecase.CreateMnemonicUseCase
import dylan.kwon.votechain.core.domain.cryptoWallet.usecase.SaveNewCryptoWalletUseCase
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NewCryptoWalletViewModel @Inject constructor(
    private val createMnemonicUseCase: CreateMnemonicUseCase,
    private val saveNewCryptoWalletUseCase: SaveNewCryptoWalletUseCase
) : MviViewModel<NewCryptoWalletUiState>(
    initialUiState = NewCryptoWalletUiState()
) {

    private val _createMnemonic = Channel<Unit>()
    private val _mnemonic = _createMnemonic.toMnemonicStateFlow()

    private val _saveCryptoWallet = Channel<Mnemonic>()
    private val _saveCryptoWalletFlow = _saveCryptoWallet.toSaveCryptoWalletFlow()

    init {
        _mnemonic.launchIn(viewModelScope)
        _saveCryptoWalletFlow.launchIn(viewModelScope)

        createMnemonic()
    }

    fun Channel<Unit>.toMnemonicStateFlow(): StateFlow<Mnemonic?> = consumeAsFlow()
        .onEach {
            onMnemonicCreationStarted()
        }
        .mapLatest {
            createMnemonicUseCase(it)
        }
        .onEach {
            onMnemonicCreationResult(it)
        }
        .map {
            it.getOrNull()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null,
        )

    fun Channel<Mnemonic>.toSaveCryptoWalletFlow() = consumeAsFlow()
        .onEach {
            onCryptoWalletSaveStarted()
        }
        .mapLatest {
            delay(500)
            saveNewCryptoWalletUseCase(it)
        }
        .onEach {
            onCryptoWalletSaveResult(it)
        }

    private fun onMnemonicCreationStarted() {
        setMnemonic(
            NewCryptoWalletUiState.Mnemonic.Loading
        )
    }

    private fun onMnemonicCreationResult(result: Result<Mnemonic>) {
        result
            .onSuccess {
                onMnemonicCreated(it)
            }
            .onFailure {
                onMnemonicCreationError()
            }
    }

    private fun onMnemonicCreated(mnemonic: Mnemonic) {
        setMnemonic(
            NewCryptoWalletUiState.Mnemonic.Loaded(
                mnemonic.words.toImmutableList()
            )
        )
    }

    private fun onMnemonicCreationError() {
        setMnemonic(
            NewCryptoWalletUiState.Mnemonic.Error
        )
    }

    private fun createMnemonic() {
        _createMnemonic.trySend(Unit)
    }

    fun refresh() {
        createMnemonic()
    }

    private fun setMnemonic(mnemonic: NewCryptoWalletUiState.Mnemonic) {
        setState {
            copy(mnemonic = mnemonic)
        }
    }

    fun save() {
        _mnemonic.value?.let {
            _saveCryptoWallet.trySend(it)
        }
    }

    private fun onCryptoWalletSaveStarted() {
        setCryptoWalletState(
            NewCryptoWalletUiState.CryptoWalletState.Saving
        )
    }

    private fun onCryptoWalletSaveResult(result: Result<Unit>) {
        when (result.isSuccess) {
            true -> onCryptoWalletSaved()

            else -> onCryptoWalletSaveError()
        }
    }

    private fun onCryptoWalletSaved() {
        setCryptoWalletState(
            NewCryptoWalletUiState.CryptoWalletState.Saved
        )
    }

    private fun onCryptoWalletSaveError() {
        setCryptoWalletState(
            NewCryptoWalletUiState.CryptoWalletState.Default
        )
    }

    private fun setCryptoWalletState(state: NewCryptoWalletUiState.CryptoWalletState) {
        setState {
            copy(
                cryptoWalletState = state
            )
        }
    }
}
