package dylan.kwon.voetchain.core.adapter.vote.mapper

import dylan.kwon.votechain.core.domain.vote.entity.BallotItem
import dylan.kwon.votechain.core.driver.vote_contract.model.BallotItem as VoteContractBallotItem

fun VoteContractBallotItem.toDomain(
    id: Int,
    isVoted: Boolean,
) = BallotItem(
    id = id,
    name = name,
    count = count,
    isVoted = isVoted
)