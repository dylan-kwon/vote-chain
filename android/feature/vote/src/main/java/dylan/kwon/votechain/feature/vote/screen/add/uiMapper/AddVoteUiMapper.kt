package dylan.kwon.votechain.feature.vote.screen.add.uiMapper

import dylan.kwon.votechain.core.domain.vote.entity.DomainVoteForm
import dylan.kwon.votechain.feature.vote.screen.add.AddVoteUiState

interface AddVoteUiMapper {

    fun AddVoteUiState.VoteForm.toDomain(): DomainVoteForm

}