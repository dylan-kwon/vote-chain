package dylan.kwon.votechain.core.domain.use_case.auth

import dylan.kwon.votechain.core.architecture.clean_architecture.SuspendUseCase
import dylan.kwon.votechain.core.domain.entity.simplePassword.SimplePassword
import dylan.kwon.votechain.core.domain.port.auth.AuthRepository
import javax.inject.Inject

class AuthSimplePasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : SuspendUseCase<SimplePassword, Boolean>() {

    override suspend fun onInvoke(input: SimplePassword): Boolean {
        return authRepository.authSimplePassword(input)
    }
}