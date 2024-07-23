package dylan.kwon.votechain.feature.vote.screen.add

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun AddVoteRoute(
    viewModel: AddVoteViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onResult: (AddVoteNavigationResult) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
}

@Composable
internal fun AddVoteScreen(
    uiState: AddVoteUiState
) {
    Scaffold {

    }
}