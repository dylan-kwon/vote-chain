package dylan.kwon.votechain.feature.auth.ui.screen.simplePassword

import androidx.annotation.StringRes
import dylan.kwon.votechain.core.domain.auth.entity.SimplePassword
import dylan.kwon.votechain.core.ui.design_system.theme.composable.messageCard.MessageCard
import dylan.kwon.votechain.feature.auth.R
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class SimplePasswordUiState(
    val inputPassword: ImmutableList<String> = persistentListOf(),
    val message: Message = Message.Default,
    val result: Boolean? = null
) {
    val passwordLength: Int = SimplePassword.PASSWORD_LENGTH

    val inputSize
        get() = inputPassword.size

    val hasInputPassword: Boolean
        get() = inputPassword.isNotEmpty()

    val isInputCompleted: Boolean = inputPassword.size == passwordLength

    enum class Message(
        @StringRes
        val stringResId: Int,
        val state: MessageCard.State
    ) {
        Default(
            stringResId = R.string.password_enter_message,
            state = MessageCard.State.Default,
        ),
        Success(
            stringResId = R.string.success,
            state = MessageCard.State.Success
        ),
        Error(
            stringResId = R.string.error,
            state = MessageCard.State.Error
        )
    }
}