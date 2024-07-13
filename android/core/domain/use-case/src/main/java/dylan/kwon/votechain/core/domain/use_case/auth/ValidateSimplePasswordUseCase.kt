package dylan.kwon.votechain.core.domain.use_case.auth

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.entity.simplePassword.SimplePassword
import javax.inject.Inject

class ValidateSimplePasswordUseCase @Inject constructor() : UseCase<SimplePassword, Boolean>() {

    override fun onInvoke(input: SimplePassword): Boolean {
        return input.isValidate
    }
}