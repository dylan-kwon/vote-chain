package dylan.kwon.votechain.core.data.vote_contract.mapper

import dylan.kwon.votechain.core.data.vote_contract.VoteContractImpl
import dylan.kwon.votechain.core.data.vote_contract.model.Voter


internal fun VoteContractImpl.Voter.toVoter() =
    Voter(
        id = id,
        votings = votings.map {
            it.toLong()
        }
    )