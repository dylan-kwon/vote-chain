@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package dylan.kwon.votechain.feature.vote.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import dylan.kwon.votechain.core.domain.vote.entity.VoteSummary
import dylan.kwon.votechain.core.ui.design_system.theme.VoteChainTheme
import dylan.kwon.votechain.feature.vote.R
import kotlinx.coroutines.flow.flowOf


@Composable
fun VoteListRoute(
    modifier: Modifier = Modifier,
    viewModel: VoteListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val voteList = viewModel.voteList.collectAsLazyPagingItems()

    VoteListScreen(
        modifier = modifier,
        uiState = uiState,
        voteList = voteList
    )
}

@Composable
internal fun VoteListScreen(
    modifier: Modifier = Modifier,
    uiState: VoteListUiState,
    voteList: LazyPagingItems<VoteSummary>
) {
    val scrollBehavior =
        TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(
            scrollBehavior.nestedScrollConnection
        ),
        topBar = {
            TopBar(
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        VoteList(
            modifier = modifier.padding(paddingValues),
            voteList = voteList
        )
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = stringResource(id = R.string.vote_list))
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun VoteList(
    modifier: Modifier = Modifier,
    voteList: LazyPagingItems<VoteSummary>
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(
            count = voteList.itemCount,
            key = voteList.itemKey {
                it.id
            },
        ) {
            val vote = voteList[it]
            when (vote) {
                null -> {
                    Text(text = "Holder")
                }

                else -> {
                    Text(text = vote.title)
                }
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun preview(modifier: Modifier = Modifier) {
    VoteChainTheme {
        VoteListScreen(
            uiState = VoteListUiState(),
            voteList = flowOf(
                PagingData.from(
                    listOf<VoteSummary>(),
                    sourceLoadStates = LoadStates(
                        refresh = LoadState.NotLoading(false),
                        append = LoadState.NotLoading(false),
                        prepend = LoadState.NotLoading(false),
                    )
                )
            ).collectAsLazyPagingItems()
        )
    }
}