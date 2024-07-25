package dylan.kwon.votechain.core.driver.vote_contract.mapper

import dylan.kwon.votechain.core.driver.vote_contract.VoteContractImpl
import dylan.kwon.votechain.core.driver.vote_contract.model.Voter


internal fun VoteContractImpl.Voter.toVoter() =
    Voter(
        id = id,
        votings = votings.map {
            it.toLong()
        }
    )