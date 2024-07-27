@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
)

package dylan.kwon.votechain.feature.vote.screen.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.HowToVote
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
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
import dylan.kwon.votechain.core.ui.compose_ext.composable.OneShotLaunchedEffect
import dylan.kwon.votechain.core.ui.design_system.theme.VoteChainTheme
import dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem.Placeholder
import dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem.VoteListItem
import dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem.VoteListItemUiState
import dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem.preview.VoteListItemUiStatesPreviewParameterProvider
import dylan.kwon.votechain.feature.vote.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow


@Composable
fun VoteListRoute(
    modifier: Modifier = Modifier,
    viewModel: VoteListViewModel = hiltViewModel(),
    onSettingsClick: () -> Unit,
    onVoteAddClick: () -> Unit,
    onVoteListItemClick: (VoteListItemUiState) -> Unit
) {
    val voteListItems = viewModel.voteList.collectAsLazyPagingItems()

    VoteListRoute(
        modifier = modifier,
        viewModel = viewModel,
        voteListItems = voteListItems,
        onSettingsClick = onSettingsClick,
        onVoteAddClick = onVoteAddClick,
        onVoteListItemClick = onVoteListItemClick,
    )
}

@Composable
fun VoteListRoute(
    modifier: Modifier = Modifier,
    viewModel: VoteListViewModel,
    voteListItems: LazyPagingItems<VoteListItemUiState>,
    onSettingsClick: () -> Unit,
    onVoteAddClick: () -> Unit,
    onVoteListItemClick: (VoteListItemUiState) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    VoteListScreen(
        modifier = modifier,
        uiState = uiState,
        voteListItems = voteListItems,
        onSearch = {
            viewModel.search()
            focusManager.clearFocus()
        },
        onSettingsClick = onSettingsClick,
        onSearchKeywordChange = viewModel::updateSearchKeyword,
        onVoteListItemClick = onVoteListItemClick,
        onVoteAddClick = onVoteAddClick
    )
}

@Composable
internal fun VoteListScreen(
    modifier: Modifier = Modifier,
    uiState: VoteListUiState,
    voteListItems: LazyPagingItems<VoteListItemUiState>,
    onSearchKeywordChange: (String) -> Unit,
    onSearch: () -> Unit,
    onSettingsClick: () -> Unit,
    onVoteListItemClick: (VoteListItemUiState) -> Unit,
    onVoteAddClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBar(
                    modifier = Modifier.weight(1f),
                    searchKeyword = uiState.searchKeyword,
                    onSearch = onSearch,
                    onSearchKeywordChange = onSearchKeywordChange
                )
                SettingButton(
                    modifier = Modifier.windowInsetsPadding(SearchBarDefaults.windowInsets),
                    onClick = onSettingsClick
                )
            }
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
private fun SettingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.Settings,
            contentDescription = stringResource(id = R.string.settings),
        )
    }
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

    // Refresh when refresh indicator is exposed.
    if (fullToRefreshState.isRefreshing) OneShotLaunchedEffect(Unit) {
        voteListItems.refresh()
    }

    // Hide refresh indicator when data refresh is complete.
    if (voteListItems.loadState.refresh !is LoadState.Loading) OneShotLaunchedEffect(Unit) {
        fullToRefreshState.endRefresh()
    }

    // Reset scroll position upon refresh completion.
    if (voteListItems.loadState.refresh is LoadState.NotLoading) OneShotLaunchedEffect {
        delay(200)
        state.scrollToItem(0)
    }

    // List is empty
    val isEmpty = voteListItems.itemCount == 0

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
            ) { index ->
                when (val vote = voteListItems[index]) {
                    null -> {
                        VoteListItem.Placeholder()
                    }

                    else -> VoteListItem(
                        modifier = Modifier.fillMaxWidth(),
                        uiState = vote,
                        onClick = {
                            onVoteListItemClick(vote)
                        }
                    )
                }
            }

            // Placeholder
            val isRefreshLoading = voteListItems.loadState.refresh is LoadState.Loading
            if (isEmpty && isRefreshLoading) {
                items(100) {
                    VoteListItem.Placeholder()
                }
            }

            // Refresh Button in List
            val isAppendError = voteListItems.loadState.append is LoadState.Error
            if (isAppendError) {
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
        val isRefreshError = voteListItems.loadState.refresh is LoadState.Error
        if (isRefreshError) {
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
            onSearchKeywordChange = {},
            onSettingsClick = {}
        )
    }
}