package dylan.kwon.votechain.core.driver.vote_contract.mapper

import dylan.kwon.votechain.core.driver.vote_contract.model.BallotItem
import dylan.kwon.votechain.core.driver.vote_contract.VoteContractImpl.BallotItem as Web3jBallotItem


internal fun Web3jBallotItem.toBallotItem() =
    BallotItem(
        name = name,
        count = count.toLong()
    )