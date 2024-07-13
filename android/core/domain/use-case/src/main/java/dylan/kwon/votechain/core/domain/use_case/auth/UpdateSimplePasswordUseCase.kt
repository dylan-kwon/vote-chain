package dylan.kwon.votechain.core.domain.use_case.auth

import dylan.kwon.votechain.core.architecture.clean_architecture.SuspendUseCase
import dylan.kwon.votechain.core.domain.entity.simplePassword.SimplePassword
import dylan.kwon.votechain.core.domain.port.auth.AuthRepository
import javax.inject.Inject

class UpdateSimplePasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val validateSimplePasswordUseCase: ValidateSimplePasswordUseCase
) : SuspendUseCase<SimplePassword, Unit>() {

    override suspend fun onInvoke(input: SimplePassword) {
        if (validateSimplePasswordUseCase(input).getOrThrow()) {
            throw IllegalArgumentException("password is invalid.")
        }
        return authRepository.updateSimplePassword(input)
    }
}