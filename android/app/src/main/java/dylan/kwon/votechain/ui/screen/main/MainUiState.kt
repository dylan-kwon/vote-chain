package dylan.kwon.votechain.ui.screen.main

import androidx.annotation.StringRes

sealed interface MainUiState {
    data class Setup(
        val isVerifiedSimplePassword: Boolean = false,
    ) : MainUiState

    data class NoSetup(
        val isAuth: Boolean = false,
        val hasCryptoWallet: Boolean = false
    ) : MainUiState {
        val needCryptoWallet: Boolean
            get() = isAuth && !hasCryptoWallet
    }

    data class Error(
        @StringRes
        val messageResId: Int
    ) : MainUiState

}
