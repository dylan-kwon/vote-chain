package dylan.kwon.votechain.feature.vote.screen.add.uiMapper

import dylan.kwon.votechain.core.domain.vote.entity.DomainVoteForm
import dylan.kwon.votechain.feature.vote.screen.add.AddVoteUiState
import javax.inject.Inject

class DefaultAddVoteUiMapper @Inject constructor() : AddVoteUiMapper {

    override fun AddVoteUiState.VoteForm.toDomain(): DomainVoteForm = DomainVoteForm(
        title = title,
        content = content,
        imageUri = imageUri,
        isAllowDuplicateVoting = isAllowDuplicateVoting,
        ballotItems = ballotItems
    )

}