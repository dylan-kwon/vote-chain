package dylan.kwon.voetchain.core.data.bundle.vote.mapper

import dylan.kwon.votechain.core.data.firebase.firestore.vote.model.VoteDocument
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary

internal fun VoteDocument.toVoteSummary(): VoteSummary = VoteSummary(
    id = id ?: -1,
    title = title.orEmpty(),
    content = content.orEmpty(),
    imageUrl = imageUrl,
    isClosed = isClosed ?: true,
    createdAt = createdAt ?: 0
)