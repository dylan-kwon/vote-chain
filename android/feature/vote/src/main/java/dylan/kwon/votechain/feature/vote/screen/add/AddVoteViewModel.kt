package dylan.kwon.votechain.feature.vote.screen.add

import dagger.hilt.android.lifecycle.HiltViewModel
import dylan.kwon.votechain.core.architecture.mvi.MviViewModel
import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject

@HiltViewModel
class AddVoteViewModel @Inject constructor() : MviViewModel<AddVoteUiState>(
    initialUiState = AddVoteUiState()
) {

    fun updateImage(imagePath: String) {
        setState {
            copy(
                vote = vote.copy(
                    imagePath = imagePath
                )
            )
        }
    }

    fun updateTitle(title: String) {
        setState {
            copy(
                vote = vote.copy(
                    title = title
                )
            )
        }
    }

    fun updateContent(content: String) {
        setState {
            copy(
                vote = vote.copy(
                    content = content
                )
            )
        }
    }

    fun addBallotItem() {
        setState {
            copy(
                vote = vote.copy(
                    ballotItems = vote.ballotItems.toMutableList()
                        .apply { add("") }
                        .toImmutableList()
                )
            )
        }
    }

    fun removeBallotItem(index: Int) {
        setState {
            copy(
                vote = vote.copy(
                    ballotItems = vote.ballotItems.toMutableList()
                        .apply { removeAt(index) }
                        .toImmutableList()
                )
            )
        }
    }

    fun updateDuplicateVotingAllow(isAllow: Boolean) {
        setState {
            copy(
                vote = vote.copy(
                    isAllowDuplicateVoting = isAllow
                )
            )
        }
    }

}