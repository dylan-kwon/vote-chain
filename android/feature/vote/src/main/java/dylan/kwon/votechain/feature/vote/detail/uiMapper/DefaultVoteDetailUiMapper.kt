package dylan.kwon.votechain.feature.vote.detail.uiMapper

import com.github.marlonlom.utilities.timeago.TimeAgo
import dylan.kwon.votechain.core.domain.vote.entity.BallotItem
import dylan.kwon.votechain.core.domain.vote.entity.Vote
import kotlinx.collections.immutable.toImmutableList
import java.util.Locale
import javax.inject.Inject
import dylan.kwon.votechain.feature.vote.detail.VoteDetailUiState.Loaded.BallotItem as BallotItemUiState
import dylan.kwon.votechain.feature.vote.detail.VoteDetailUiState.Loaded.Vote as VoteUiState

class DefaultVoteDetailUiMapper @Inject constructor() : VoteDetailUiMapper {
    override fun Vote.toUiState(): VoteUiState = VoteUiState(
        title = title,
        content = content,
        imageUrl = imageUrl,
        voteStatus = when (isClosed) {
            true -> VoteUiState.Status.CLOSED
            else -> VoteUiState.Status.IN_PROGRESS
        },
        voterCount = String.format(
            Locale.getDefault(), "%,d", voterCount
        ),
        createdAgo = TimeAgo.using(createdAt),
        ballotItems = ballotItems.map {
            it.toUiState()
        }.toImmutableList()
    )

    override fun BallotItem.toUiState(): BallotItemUiState = BallotItemUiState(
        id = id,
        name = name,
        votingCount = count,
        isVoted = isVoted
    )
}