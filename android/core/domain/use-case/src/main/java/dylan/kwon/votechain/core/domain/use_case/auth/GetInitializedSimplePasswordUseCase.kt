package dylan.kwon.votechain.core.domain.use_case.auth

import dylan.kwon.votechain.core.architecture.clean_architecture.FlowUseCase
import dylan.kwon.votechain.core.domain.port.auth.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetInitializedSimplePasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : FlowUseCase<Unit, Boolean>() {

    override fun onInvoke(input: Unit): Flow<Boolean> {
        return authRepository.isInitializedSimplePassword
    }
}