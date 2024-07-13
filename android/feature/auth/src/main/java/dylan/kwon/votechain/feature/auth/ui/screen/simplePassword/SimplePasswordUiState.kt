package dylan.kwon.votechain.feature.auth.ui.screen.simplePassword

import androidx.annotation.StringRes
import dylan.kwon.votechain.core.domain.entity.simplePassword.SimplePassword
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class SimplePasswordUiState(
    val password: ImmutableList<String> = persistentListOf(),
    val isPasswordValid: Boolean = false,
    val maximumPasswordSize: Int = SimplePassword.MAXIMUM_PASSWORD_SIZE,

    @StringRes
    val messageResId: Int? = null,
) {
    val inputPasswordSize
        get() = password.size

    val hasPassword: Boolean
        get() = password.isNotEmpty()

    val isInputComplete: Boolean = password.size == maximumPasswordSize

    val hasMessage: Boolean
        get() = messageResId != null
}