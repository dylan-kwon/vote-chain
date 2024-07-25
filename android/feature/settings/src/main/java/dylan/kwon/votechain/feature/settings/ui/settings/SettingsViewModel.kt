@file:OptIn(ExperimentalCoroutinesApi::class)

package dylan.kwon.votechain.feature.settings.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dylan.kwon.votechain.core.domain.cryptoWallet.entity.CryptoWallet
import dylan.kwon.votechain.core.domain.cryptoWallet.usecase.GetSavedCryptoWalletUseCase
import dylan.kwon.votechain.feature.settings.ui.settings.uiMapper.SettingsUiMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val uiMapper: SettingsUiMapper,
    private val getCryptoWalletUseCase: GetSavedCryptoWalletUseCase
) : ViewModel(), SettingsUiMapper by uiMapper {

    val cryptoWallet: StateFlow<CryptoWallet?> = MutableStateFlow(Unit)
        .mapLatest {
            getCryptoWalletUseCase(Unit).getOrNull()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null
        )

    private val isVerified = MutableStateFlow(false)

    val uiState: StateFlow<SettingsUiState> = combine(
        cryptoWallet,
        isVerified
    ) { cryptoWallet, isVerified ->
        combineState(cryptoWallet, isVerified)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = SettingsUiState.Loading
    )

    fun verified() {
        isVerified.value = true
    }

}