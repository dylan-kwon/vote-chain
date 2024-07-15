package dylan.kwon.votechain.feature.crypto_wallet.ui.newCryptoWallet

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dylan.kwon.votechain.core.architecture.mvi.MviViewModel
import dylan.kwon.votechain.core.domain.cryptoWallet.usecase.CreateMnemonicUseCase
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewCryptoWalletViewModel @Inject constructor(
    private val createMnemonicUseCase: CreateMnemonicUseCase
) : MviViewModel<NewCryptoWalletUiState>(
    initialUiState = NewCryptoWalletUiState()
) {

    private val _createMnemonic: Channel<Unit> = Channel()
    private val createMnemonic: ReceiveChannel<Unit> = _createMnemonic

    init {
        onCreateNewMnemonicFlow().launchIn(viewModelScope)

        createMnemonic()
    }

    fun onCreateNewMnemonicFlow(): Flow<ImmutableList<String>> = createMnemonic.consumeAsFlow()
        .onEach {
            setMnemonic(
                NewCryptoWalletUiState.Mnemonic.Loading
            )
        }
        .mapLatest {
            this.createMnemonicUseCase(it).getOrThrow()
        }
        .map {
            it.words.toImmutableList()
        }
        .onEach {
            setMnemonic(
                NewCryptoWalletUiState.Mnemonic.Loaded(it)
            )
        }
        .catch {
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
}
