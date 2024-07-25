package dylan.kwon.voetchain.core.adapter.vote.mapper

import dylan.kwon.votechain.core.domain.vote.entity.VoteForm
import dylan.kwon.votechain.core.driver.vote_contract.model.CreateVoteData as VoteContractForm

internal fun VoteForm.toCreateVoteData() = VoteContractForm(
    title = title,
    content = content,
    imageUrl = imageUri,
    isAllowDuplicateVoting = isAllowDuplicateVoting,
    ballotItems = ballotItems
)