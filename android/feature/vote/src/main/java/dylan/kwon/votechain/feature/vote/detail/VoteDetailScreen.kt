@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package dylan.kwon.votechain.feature.vote.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun VoteDetailRoute(
    viewModel: VoteDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    VoteDetailScreen(
        uiState = uiState,
        onBackClick = onBackClick
    )
}

@Composable
internal fun VoteDetailScreen(
    uiState: VoteDetailUiState,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(
                title = uiState.title,
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Text(text = "Detail")
        }
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick,
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}