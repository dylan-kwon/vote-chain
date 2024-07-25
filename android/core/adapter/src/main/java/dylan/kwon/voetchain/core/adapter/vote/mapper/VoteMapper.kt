package dylan.kwon.voetchain.core.adapter.vote.mapper

import dylan.kwon.votechain.core.domain.vote.entity.Vote
import dylan.kwon.votechain.core.driver.vote_contract.model.BallotItem as VoteContractBallotItem
import dylan.kwon.votechain.core.driver.vote_contract.model.Vote as VoteContractVote

internal fun VoteContractVote.toDomain(
    isOwner: Boolean,
    voting: Set<Long>,
    ballotItems: List<VoteContractBallotItem>,
) = Vote(
    id = id,
    title = title,
    content = content,
    imageUrl = imageUrl.takeIf {
        it.isNotEmpty()
    },
    voterCount = voterCount,
    isClosed = isClosed,
    isAllowDuplicateVoting = isAllowDuplicateVoting,
    ballotItems = ballotItems.mapIndexed { index, ballotItem ->
        ballotItem.toDomain(
            id = index,
            isVoted = voting.contains(index.toLong())
        )
    },
    isOwner = isOwner,
    isVoted = voting.isNotEmpty(),
    createdAt = createdAt
)