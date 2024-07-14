package dylan.kwon.votechain.core.domain.auth.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.SuspendUseCase
import dylan.kwon.votechain.core.domain.auth.entity.SimplePassword
import dylan.kwon.votechain.core.domain.auth.port.AuthRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class SubmitSimplePasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : SuspendUseCase<SimplePassword, Boolean>() {

    override suspend fun onInvoke(input: SimplePassword): Boolean {
        if (!input.isValidate) {
            return false
        }
        return when (isInitialized()) {
            true -> onInitialized(input)
            else -> onUninitialized(input)
        }
    }

    private suspend fun isInitialized() =
        authRepository.isInitializedSimplePassword.first()

    private suspend fun onInitialized(password: SimplePassword): Boolean {
        return authRepository.authSimplePassword(password)
    }

    private suspend fun onUninitialized(password: SimplePassword): Boolean {
        authRepository.updateSimplePassword(password)
        return true
    }
}