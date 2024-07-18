package dylan.kwon.votechain.feature.vote.list

import dagger.hilt.android.lifecycle.HiltViewModel
import dylan.kwon.votechain.core.architecture.mvi.MviViewModel
import dylan.kwon.votechain.core.domain.vote.usecase.GetVoteSummariesUseCase
import javax.inject.Inject

@HiltViewModel
class VoteListViewModel @Inject constructor(
    getVoteSummariesUseCase: GetVoteSummariesUseCase
) : MviViewModel<VoteListUiState>(
    initialUiState = VoteListUiState()
) {

    val voteList = getVoteSummariesUseCase(Unit)


}