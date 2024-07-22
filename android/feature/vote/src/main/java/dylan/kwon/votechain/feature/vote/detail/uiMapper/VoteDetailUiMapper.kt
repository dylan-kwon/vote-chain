package dylan.kwon.votechain.feature.vote.detail.uiMapper

import dylan.kwon.votechain.core.domain.vote.entity.BallotItem
import dylan.kwon.votechain.core.domain.vote.entity.Vote
import dylan.kwon.votechain.feature.vote.detail.VoteDetailUiState.Loaded.BallotItem as BallotItemUiState
import dylan.kwon.votechain.feature.vote.detail.VoteDetailUiState.Loaded.Vote as VoteUiState

interface VoteDetailUiMapper {

    fun Vote.toUiState(): VoteUiState

    fun BallotItem.toUiState(): BallotItemUiState

}