@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
)

package dylan.kwon.votechain.feature.vote.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.HowToVote
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import dylan.kwon.votechain.core.ui.compose_ext.OneShotLaunchedEffect
import dylan.kwon.votechain.core.ui.design_system.theme.VoteChainTheme
import dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem.Placeholder
import dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem.VoteListItem
import dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem.VoteListItemUiState
import dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem.preview.VoteListItemUiStatesPreviewParameterProvider
import dylan.kwon.votechain.feature.vote.R
import kotlinx.coroutines.flow.Flow


@Composable
fun VoteListRoute(
    modifier: Modifier = Modifier,
    viewModel: VoteListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val voteList = viewModel.voteList.collectAsLazyPagingItems()

    val focusManager = LocalFocusManager.current

    VoteListScreen(
        modifier = modifier,
        uiState = uiState,
        voteListItems = voteList,
        onSearch = {
            viewModel.search()
            focusManager.clearFocus()
        },
        onSearchKeywordChange = viewModel::updateSearchKeyword,
        onVoteListItemClick = {
            // todo:
        },
        onVoteAddClick = {
            // todo:
        }
    )
}

@Composable
internal fun VoteListScreen(
    modifier: Modifier = Modifier,
    uiState: VoteListUiState,
    voteListItems: LazyPagingItems<VoteListItemUiState>,
    onSearchKeywordChange: (String) -> Unit,
    onSearch: () -> Unit,
    onVoteListItemClick: (VoteListItemUiState) -> Unit,
    onVoteAddClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                searchKeyword = uiState.searchKeyword,
                onSearch = onSearch,
                onSearchKeywordChange = onSearchKeywordChange
            )
        },
        floatingActionButton = {
            AddVoteButton(
                onClick = onVoteAddClick
            )
        }
    ) { paddingValues ->
        VoteList(
            modifier = modifier.padding(paddingValues),
            voteListItems = voteListItems,
            onVoteListItemClick = onVoteListItemClick
        )
    }
}

@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    searchKeyword: String,
    onSearchKeywordChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    SearchBar(
        modifier = modifier,
        query = searchKeyword,
        onQueryChange = onSearchKeywordChange,
        onSearch = {
            onSearch()
        },
        active = false,
        onActiveChange = {},
        placeholder = {
            Text(text = stringResource(id = R.string.search))
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.HowToVote,
                contentDescription = null
            )
        },
        trailingIcon = {
            IconButton(onClick = onSearch) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            }
        },
        content = { }
    )
}

@Composable
private fun VoteList(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    voteListItems: LazyPagingItems<VoteListItemUiState>,
    onVoteListItemClick: (VoteListItemUiState) -> Unit
) {
    // Refresh.
    val fullToRefreshState = rememberPullToRefreshState()

    // Refresh indicator exposed on first composition.
    OneShotLaunchedEffect {
        fullToRefreshState.startRefresh()
    }

    // Refresh when refresh indicator is exposed.
    if (fullToRefreshState.isRefreshing) OneShotLaunchedEffect(Unit) {
        voteListItems.refresh()
    }

    // Hide refresh indicator when data refresh is complete.
    if (voteListItems.loadState.refresh !is LoadState.Loading) OneShotLaunchedEffect(Unit) {
        fullToRefreshState.endRefresh()
    }

    // Reset scroll position upon refresh completion.
    if (voteListItems.loadState.refresh is LoadState.Loading) OneShotLaunchedEffect {
        state.scrollToItem(0)
    }

    // List + Pull to Refresh
    Box(
        modifier = modifier.nestedScroll(
            fullToRefreshState.nestedScrollConnection
        )
    ) {
        // List
        LazyColumn(
            modifier = Modifier.padding(top = 24.dp),
            state = state
        ) {

            // VoteListItems
            items(
                count = voteListItems.itemCount,
                key = voteListItems.itemKey {
                    it.id
                },
                contentType = {
                    VoteListItemUiState::class
                }
            ) {
                when (val vote = voteListItems[it]) {
                    null -> VoteListItem.Placeholder()
                    else -> VoteListItem(
                        modifier = Modifier.fillMaxWidth(),
                        uiState = vote,
                        onClick = {
                            onVoteListItemClick(vote)
                        }
                    )
                }
            }

            // Refresh Button in List
            if (voteListItems.loadState.append is LoadState.Error) {
                item(
                    key = -1,
                    contentType = {
                        LoadState.Error::class
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        RetryButton(
                            onRetryClick = {
                                fullToRefreshState.startRefresh()
                            }
                        )
                    }
                }
            }
        }

        // Retry Button
        if (voteListItems.loadState.refresh is LoadState.Error) {
            RetryButton(
                modifier = Modifier.align(Alignment.Center),
                onRetryClick = {
                    fullToRefreshState.startRefresh()
                }
            )
        }

        // Refresh Indicator
        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = fullToRefreshState
        )
    }
}

@Composable
fun RetryButton(
    modifier: Modifier = Modifier,
    onRetryClick: () -> Unit
) {
    ElevatedButton(
        modifier = modifier,
        onClick = onRetryClick
    ) {
        Text(text = stringResource(id = R.string.retry))
    }
}

@Composable
private fun AddVoteButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.add)
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview(
    @PreviewParameter(VoteListItemUiStatesPreviewParameterProvider::class)
    voteListItem: Flow<PagingData<VoteListItemUiState>>
) {
    VoteChainTheme {
        VoteListScreen(
            uiState = VoteListUiState(),
            voteListItems = voteListItem.collectAsLazyPagingItems(),
            onVoteListItemClick = {},
            onVoteAddClick = {},
            onSearch = {},
            onSearchKeywordChange = {}
        )
    }
}