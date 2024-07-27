@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package dylan.kwon.votechain.feature.vote.screen.add

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dylan.kwon.votechain.core.domain.vote.entity.DomainVote
import dylan.kwon.votechain.core.ui.design_system.theme.VoteChainTheme
import dylan.kwon.votechain.core.ui.design_system.theme.composable.image.VoteChainImage
import dylan.kwon.votechain.feature.vote.R
import dylan.kwon.votechain.feature.vote.screen.add.preview.AddVoteUiStatePreviewParameterProvider
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun AddVoteRoute(
    viewModel: AddVoteViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onResult: (AddVoteNavigationResult) -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.toString()?.let { uriString ->
            viewModel.updateImageUri(uriString)
        }
    }

    AddVoteScreen(
        uiState = uiState,
        onBackClick = onBackClick,
        onTitleChange = viewModel::updateTitle,
        onContentChange = viewModel::updateContent,
        onAddBallotItemClick = viewModel::addBallotItem,
        onBallotItemChange = viewModel::updateBallotItem,
        onBallotItemRemove = viewModel::removeBallotItem,
        onDuplicateVotingAllowCheckedChange = viewModel::updateDuplicateVotingAllow,
        onImageClick = {
            imagePickerLauncher.launch("image/*")
        },
        onSubmitClick = {
            viewModel.create()
            focusManager.clearFocus()
        },
    )

    if (uiState.toastMessage != null) LaunchedEffect(Unit) {
        Toast.makeText(context, uiState.toastMessage, Toast.LENGTH_SHORT).show()
        viewModel.consumeToastMessage()
    }

    if (uiState.isCreated) LaunchedEffect(Unit) {
        onResult(AddVoteNavigationResult(isCreated = true))
    }
}

@Composable
internal fun AddVoteScreen(
    uiState: AddVoteUiState,
    onBackClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    onAddBallotItemClick: () -> Unit,
    onBallotItemChange: (Int, String) -> Unit,
    onBallotItemRemove: (Int) -> Unit,
    onDuplicateVotingAllowCheckedChange: (Boolean) -> Unit,
    onImageClick: () -> Unit,
    onSubmitClick: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .nestedScroll(
                scrollBehavior.nestedScrollConnection
            )
            .imePadding(),
        topBar = {
            TopBar(
                scrollBehavior = scrollBehavior,
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            // Vote Form
            VoteForm(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                voteForm = uiState.voteForm,
                isSubmitButtonEnabled = uiState.isSubmitButtonEnabled,
                onTitleChange = onTitleChange,
                onContentChange = onContentChange,
                onAddBallotItemClick = onAddBallotItemClick,
                onBallotItemChange = onBallotItemChange,
                onBallotItemRemove = onBallotItemRemove,
                onDuplicateVotingAllowCheckedChange = onDuplicateVotingAllowCheckedChange,
                onImageClick = onImageClick,
                onSubmitClick = onSubmitClick,
            )

            // Progress Bar
            AnimatedVisibility(uiState.isProgressbarVisible) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun VoteForm(
    modifier: Modifier = Modifier,
    voteForm: AddVoteUiState.VoteForm,
    isSubmitButtonEnabled: Boolean,
    onAddBallotItemClick: () -> Unit,
    onSubmitClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    onBallotItemChange: (Int, String) -> Unit,
    onBallotItemRemove: (Int) -> Unit,
    onDuplicateVotingAllowCheckedChange: (Boolean) -> Unit,
    onImageClick: () -> Unit,
) {
    val ballotItemsWithIndex = remember(voteForm.ballotItems) {
        voteForm.ballotItems.withIndex().toImmutableList()
    }
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)
    ) {
        // Title
        item(
            key = AddVoteUiState.VoteForm.Type.TITLE,
            contentType = AddVoteUiState.VoteForm.Type.TITLE,
        ) {
            Title(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItem(),
                title = voteForm.title,
                onValueChange = onTitleChange,
            )
        }

        // Image
        item(
            key = AddVoteUiState.VoteForm.Type.IMAGE,
            contentType = AddVoteUiState.VoteForm.Type.IMAGE
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2 / 1f)
                    .animateItem()
                    .clickable(onClick = onImageClick),
                imageUri = voteForm.imageUri
            )
        }

        // Content
        item(
            key = AddVoteUiState.VoteForm.Type.CONTENT,
            contentType = AddVoteUiState.VoteForm.Type.CONTENT
        ) {
            Content(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(160.dp)
                    .animateItem(),
                content = voteForm.content,
                onValueChange = onContentChange,
            )
        }

        // Ballot Items
        items(
            items = ballotItemsWithIndex,
            key = {
                it.index
            },
            contentType = {
                AddVoteUiState.VoteForm.Type.BALLOT_ITEM
            }
        ) {
            BallotItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItem(),
                index = it.index,
                name = it.value,
                isRemoveButtonVisible = it.index >= DomainVote.BALLOT_ITEMS_MINIMUM_SIZE,
                onValueChange = { name ->
                    onBallotItemChange(it.index, name)
                },
                onRemoveClick = {
                    onBallotItemRemove(it.index)
                }
            )

        }

        // Add Ballot Item Button
        if (voteForm.isAddBallotItemButtonVisible) {
            item(
                key = AddVoteUiState.VoteForm.Type.ADD_BALLOT_ITEM_BUTTON,
                contentType = AddVoteUiState.VoteForm.Type.ADD_BALLOT_ITEM_BUTTON
            ) {
                AddBallotItemButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                        .animateItem(),
                    onClick = onAddBallotItemClick,
                )
            }
        }

        // Duplication Allow Check Box
        item(
            key = AddVoteUiState.VoteForm.Type.DUPLICATE_VOTING_CHECKBOX,
            contentType = AddVoteUiState.VoteForm.Type.DUPLICATE_VOTING_CHECKBOX
        ) {
            DuplicationAllowCheckBox(
                modifier = Modifier.animateItem(),
                isChecked = voteForm.isAllowDuplicateVoting,
                onCheckedChange = onDuplicateVotingAllowCheckedChange
            )
        }

        // Submit Button
        item(
            key = AddVoteUiState.VoteForm.Type.SUBMIT_BUTTON,
            contentType = AddVoteUiState.VoteForm.Type.SUBMIT_BUTTON
        ) {
            SubmitButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItem(),
                enabled = isSubmitButtonEnabled,
                onClick = onSubmitClick
            )
        }
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.go_back)
                )
            }
        },
        title = {
            Text(text = stringResource(id = R.string.add_vote))
        },
        scrollBehavior = scrollBehavior
    )
}

@Composable
private fun Title(
    modifier: Modifier = Modifier,
    title: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = title,
        isError = title.isBlank(),
        onValueChange = onValueChange,
        placeholder = {
            Text(text = stringResource(id = R.string.title))
        }
    )
}

@Composable
private fun Image(
    modifier: Modifier = Modifier,
    imageUri: String?,
) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        VoteChainImage(
            modifier = modifier.clip(MaterialTheme.shapes.extraSmall),
            model = imageUri,
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(id = R.string.image),
            loading = null,
            error = null,
        )
        if (imageUri == null) {
            Icon(
                modifier = Modifier.size(48.dp),
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(id = R.string.add)
            )
        }
    }
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    content: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = content,
        isError = content.isBlank(),
        onValueChange = onValueChange,
        placeholder = {
            Text(text = stringResource(id = R.string.content))
        }
    )
}

@Composable
private fun BallotItem(
    modifier: Modifier = Modifier,
    index: Int,
    name: String,
    isRemoveButtonVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    onRemoveClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        OutlinedTextField(
            modifier = Modifier.weight(1f),
            value = name,
            onValueChange = onValueChange,
            isError = name.isBlank(),
            maxLines = 1,
            placeholder = {
                Text(text = stringResource(id = R.string.ballot_item_placeholder, index))
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
        )
        if (isRemoveButtonVisible) {
            IconButton(
                modifier = Modifier,
                onClick = onRemoveClick
            ) {
                Icon(
                    imageVector = Icons.Default.RemoveCircle,
                    contentDescription = stringResource(id = R.string.remove)
                )
            }
        }
    }
}

@Composable
private fun AddBallotItemButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.AddCircleOutline,
            contentDescription = stringResource(id = R.string.add)
        )
    }
}

@Composable
private fun DuplicationAllowCheckBox(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
        Text(
            text = stringResource(id = R.string.multiple_votes_allowed)
        )
    }
}

@Composable
private fun SubmitButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    onClick: () -> Unit
) {
    ElevatedButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick
    ) {
        Text(text = stringResource(id = R.string.submit))
    }
}

@Composable
@Preview(showBackground = true)
private fun Preview(
    @PreviewParameter(AddVoteUiStatePreviewParameterProvider::class)
    uiState: AddVoteUiState
) {
    VoteChainTheme {
        AddVoteScreen(
            uiState = uiState,
            onBackClick = {},
            onAddBallotItemClick = {},
            onSubmitClick = {},
            onImageClick = {},
            onTitleChange = {},
            onContentChange = {},
            onBallotItemChange = { _, _ -> },
            onBallotItemRemove = {},
            onDuplicateVotingAllowCheckedChange = {}
        )
    }
}