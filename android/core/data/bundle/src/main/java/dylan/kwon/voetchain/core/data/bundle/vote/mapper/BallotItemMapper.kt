package dylan.kwon.voetchain.core.data.bundle.vote.mapper

import dylan.kwon.votechain.core.domain.vote.entity.BallotItem
import dylan.kwon.votechain.core.data.vote_contract.VoteContractImpl.BallotItem as ContractBallotItem

fun ContractBallotItem.toDomain() = BallotItem(
    name = name,
    count = count
)