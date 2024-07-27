@file:Suppress("UnusedReceiverParameter")

package dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.valentinilk.shimmer.shimmer
import dylan.kwon.votechain.core.ui.design_system.R

object VoteListItem

@Composable
fun VoteListItem(
    modifier: Modifier = Modifier,
    uiState: VoteListItemUiState,
    onClick: () -> Unit,
) {
    ListItem(
        modifier = modifier.clickable(onClick = onClick),
        overlineContent = {
            Status(status = uiState.status)
        },
        headlineContent = {
            Text(text = uiState.title, maxLines = 1)
        },
        supportingContent = {
            Text(text = uiState.content)
        },
        trailingContent = {
            TimeAgo(createdAt = uiState.createdAt)
        }
    )
}

@Composable
private fun Status(
    modifier: Modifier = Modifier,
    status: VoteListItemUiState.Status
) {
    val typography = MaterialTheme.typography
    val colorScheme = MaterialTheme.colorScheme

    val textStyle = remember(typography, colorScheme, status) {

        when (status) {
            VoteListItemUiState.Status.IN_PROGRESS -> typography.labelSmall.copy(
                color = colorScheme.primary
            )

            VoteListItemUiState.Status.CLOSED -> typography.labelSmall
        }
    }
    val stringResId = remember(status) {
        when (status) {
            VoteListItemUiState.Status.IN_PROGRESS -> R.string.inprogress
            VoteListItemUiState.Status.CLOSED -> R.string.closed
        }
    }
    Text(
        modifier = modifier,
        text = stringResource(id = stringResId),
        style = textStyle
    )
}

@Composable
private fun TimeAgo(
    modifier: Modifier = Modifier,
    createdAt: Long
) {
    val timeAgo = remember(createdAt) {
        TimeAgo.using(createdAt)
    }
    Text(
        modifier = modifier,
        text = timeAgo
    )
}

@Composable
fun VoteListItem.Placeholder(
    modifier: Modifier = Modifier
) {
    ListItem(
        modifier = modifier.shimmer(),
        overlineContent = {
            PlaceholderShimmer(length = 10)
        },
        headlineContent = {
            PlaceholderShimmer(
                modifier = Modifier.padding(top = 4.dp),
                length = 20
            )
        },
        supportingContent = {
            PlaceholderShimmer(
                modifier = Modifier.padding(top = 4.dp),
                length = 40
            )
        },
        trailingContent = {
            PlaceholderShimmer(
                modifier = Modifier.padding(top = 4.dp),
                length = 10
            )
        }
    )
}

@Composable
private fun VoteListItem.PlaceholderShimmer(
    modifier: Modifier = Modifier,
    length: Int
) {
    Text(
        modifier = modifier.background(MaterialTheme.colorScheme.surfaceDim),
        text = " ".repeat(length)
    )
}