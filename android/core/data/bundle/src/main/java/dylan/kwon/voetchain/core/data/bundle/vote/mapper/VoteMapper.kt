package dylan.kwon.voetchain.core.data.bundle.vote.mapper

import dylan.kwon.votechain.core.domain.vote.entity.Vote
import dylan.kwon.votechain.core.data.vote_contract.VoteContractImpl.BallotItem as VoteContractBallotItem
import dylan.kwon.votechain.core.data.vote_contract.model.Vote as VoteContractVote

internal fun VoteContractVote.toDomain(
    ballotItems: List<VoteContractBallotItem>,
) = Vote(
    id = id.toLong(),
    title = title,
    content = content,
    imageUrl = imageUrl.takeIf {
        it.isNotEmpty()
    },
    isClosed = isClosed,
    isAllowDuplicateVoting = isAllowDuplicateVoting,
    ballotItems = ballotItems.map {
        it.toDomain()
    },
    createdAt = createdAt.toLong()
)