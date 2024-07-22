@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package dylan.kwon.votechain.feature.vote.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import dylan.kwon.votechain.feature.vote.R
import dylan.kwon.votechain.feature.vote.detail.Placeholder
import dylan.kwon.votechain.feature.vote.detail.VoteDetailScreen

@Composable
internal fun VoteDetailScreen.TopBar(
    modifier: Modifier = Modifier,
    title: String,
    isOwner: Boolean,
    isLoading: Boolean,
    onBackClick: () -> Unit,
    onVoteCloseClick: () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        title = {
            when (isLoading) {
                true -> VoteDetailScreen.Placeholder(
                    modifier
                        .shimmer()
                        .size(120.dp, height = 24.dp)
                )

                else -> Text(
                    text = title
                )
            }
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
        },
        actions = {
            if (isOwner) Menus(
                onVoteCloseClick = onVoteCloseClick
            )
        }
    )
}

@Composable
private fun Menus(
    onVoteCloseClick: () -> Unit
) {
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    MoreMenuButton {
        isExpanded = true
    }
    DropdownMenu(
        expanded = isExpanded,
        onDismissRequest = {
            isExpanded = false
        },
    ) {
        DropdownMenuItem(
            text = {
                Text(text = stringResource(id = R.string.end_voting))
            },
            onClick = {
                isExpanded = false
                onVoteCloseClick()
            }
        )
    }
}

@Composable
private fun MoreMenuButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(id = R.string.more),
        )
    }
}