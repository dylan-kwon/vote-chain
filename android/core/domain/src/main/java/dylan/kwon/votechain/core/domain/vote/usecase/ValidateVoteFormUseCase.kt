package dylan.kwon.votechain.core.domain.vote.usecase

import dylan.kwon.votechain.core.architecture.clean_architecture.UseCase
import dylan.kwon.votechain.core.domain.vote.entity.VoteForm
import javax.inject.Inject

class ValidateVoteFormUseCase @Inject constructor() : UseCase<VoteForm, Boolean>() {

    override suspend fun onInvoke(input: VoteForm): Boolean {
        return input.isValid()
    }

}