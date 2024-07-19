package dylan.kwon.votechain.core.ui.design_system.theme.composable.vote.listItem

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.marlonlom.utilities.timeago.TimeAgo
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
            Text(text = uiState.title)
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
fun VoteListItem.Placeholder() {
    ListItem(headlineContent = {})
}

@Composable
private fun Status(status: VoteListItemUiState.Status) {

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
        text = stringResource(id = stringResId),
        style = textStyle
    )
}

@Composable
private fun TimeAgo(createdAt: Long) {
    val timeAgo = remember(createdAt) {
        TimeAgo.using(createdAt)
    }
    Text(text = timeAgo)
}