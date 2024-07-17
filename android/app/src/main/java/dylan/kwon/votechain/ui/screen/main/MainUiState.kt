package dylan.kwon.votechain.ui.screen.main

import androidx.annotation.StringRes

sealed interface MainUiState {
    data object Setup : MainUiState

    data class NoSetup(
        val needCryptoWallet: Unit? = null
    ) : MainUiState

    data class Error(
        @StringRes
        val messageResId: Int
    ) : MainUiState

}
