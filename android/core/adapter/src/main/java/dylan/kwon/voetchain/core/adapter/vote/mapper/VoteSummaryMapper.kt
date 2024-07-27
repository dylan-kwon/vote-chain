package dylan.kwon.voetchain.core.adapter.vote.mapper

import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary
import dylan.kwon.votechain.core.driver.firebase.firestore.vote.model.VoteDocument

internal fun VoteDocument.toDomain(): VoteSummary = VoteSummary(
    id = id ?: -1,
    title = title.orEmpty(),
    content = content.orEmpty(),
    imageUrl = imageUrl,
    isClosed = isClosed ?: true,
    createdAt = (createdAt ?: 0) * 1000
)