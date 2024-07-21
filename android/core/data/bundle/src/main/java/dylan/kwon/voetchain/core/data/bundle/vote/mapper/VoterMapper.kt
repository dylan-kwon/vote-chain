package dylan.kwon.voetchain.core.data.bundle.vote.mapper

import dylan.kwon.votechain.core.domain.vote.entity.Voter
import dylan.kwon.votechain.core.data.vote_contract.model.Voter as ContractVoter

internal fun ContractVoter.toDomain() = Voter(
    id = id,
    votings = votings
)