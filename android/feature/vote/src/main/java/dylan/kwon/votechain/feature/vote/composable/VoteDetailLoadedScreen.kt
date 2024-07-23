@file:OptIn(ExperimentalLayoutApi::class, ExperimentalLayoutApi::class)

package dylan.kwon.votechain.feature.vote.composable

import android.text.Layout
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HowToVote
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.compose.common.of
import com.patrykandpatrick.vico.compose.common.shape.rounded
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.columnSeries
import com.patrykandpatrick.vico.core.cartesian.layer.ColumnCartesianLayer
import com.patrykandpatrick.vico.core.cartesian.marker.DefaultCartesianMarker
import com.patrykandpatrick.vico.core.common.Defaults
import com.patrykandpatrick.vico.core.common.Dimensions
import com.patrykandpatrick.vico.core.common.component.TextComponent
import com.patrykandpatrick.vico.core.common.shape.Shape
import dylan.kwon.votechain.core.ui.design_system.theme.VoteChainTheme
import dylan.kwon.votechain.core.ui.design_system.theme.composable.iconWithText.IconWithText
import dylan.kwon.votechain.core.ui.design_system.theme.composable.image.VoteChainImage
import dylan.kwon.votechain.feature.vote.R
import dylan.kwon.votechain.feature.vote.composable.preview.VoteDetailLoadedUiStatePreviewParameterProvider
import dylan.kwon.votechain.feature.vote.detail.VoteDetailScreen
import dylan.kwon.votechain.feature.vote.detail.VoteDetailUiState
import kotlinx.collections.immutable.ImmutableList
import dylan.kwon.votechain.feature.vote.detail.VoteDetailUiState.Loaded.BallotItem as BallotItemUiState
import dylan.kwon.votechain.feature.vote.detail.VoteDetailUiState.Loaded.Vote as VoteUiState

@Composable
fun VoteDetailScreen.Loaded(
    modifier: Modifier = Modifier,
    uiState: VoteDetailUiState.Loaded,
    scrollState: ScrollState = rememberScrollState(),
    onBallotItemCheckedChange: (BallotItemUiState) -> Unit,
    onSubmitClick: () -> Unit,
    onConsumedToastMessage: () -> Unit
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Time Ago
            TimeAgo(
                modifier = Modifier.align(Alignment.End),
                timeAgo = uiState.vote.createdAgo
            )

            // Image
            val imageUrl = uiState.vote.imageUrl
            if (imageUrl != null) {
                VoteImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2 / 1f),
                    imageUrl = imageUrl,
                    contentDescription = uiState.vote.title
                )
            }

            // Status & Voter Count
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Status
                VoteStatus(
                    status = uiState.vote.voteStatus
                )

                // Voter Count
                VoterCount(
                    voterCount = uiState.vote.voterCount
                )
            }

            // Content
            Content(
                text = uiState.vote.content
            )

            // Ballot Items
            AnimatedVisibility(uiState.canVoting) {
                BallotItems(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    ballotItems = uiState.vote.ballotItems,
                    enabled = uiState.canVoting,
                    canMultipleChoice = uiState.canMultipleChoice,
                    onCheckedChange = onBallotItemCheckedChange
                )
            }

            // Submit Button
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.End),
                visible = uiState.canVoting
            ) {
                SubmitButton(
                    enabled = uiState.isSubmitButtonEnabled,
                    onClick = onSubmitClick
                )
            }

            // Statistics
            AnimatedVisibility(!uiState.canVoting) {
                Statistics(
                    modifier = Modifier.fillMaxWidth(),
                    ballotItems = uiState.vote.ballotItems
                )
            }
        }

        // Progress Bar
        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(top = 16.dp),
            visible = uiState.isProgressShowing
        ) {
            CircularProgressIndicator()
        }
    }
    val context = LocalContext.current
    val toastMessage = uiState.toastMessage
    if (toastMessage != null) LaunchedEffect(Unit) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        onConsumedToastMessage()
    }
}

@Composable
private fun TimeAgo(
    modifier: Modifier = Modifier,
    timeAgo: String
) {
    Text(
        modifier = modifier,
        text = timeAgo,
        style = MaterialTheme.typography.labelMedium
    )
}

@Composable
private fun VoteImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    contentDescription: String
) {
    VoteChainImage(
        modifier = modifier.clip(RoundedCornerShape(4.dp)),
        model = imageUrl,
        contentScale = ContentScale.Crop,
        contentDescription = contentDescription,
    )
}

@Composable
private fun VoteStatus(
    modifier: Modifier = Modifier,
    status: VoteUiState.Status
) {
    IconWithText(
        modifier = modifier,
        imageVector = status.toImageVector(),
        contentDescription = stringResource(id = R.string.status),
        text = status.toLabel(),
        textColor = status.toTextColor(),
        backgroundColor = status.toBackgroundColor()
    )
}

@Composable
private fun VoteUiState.Status.toImageVector(): ImageVector {
    return when (this) {
        VoteUiState.Status.IN_PROGRESS -> Icons.Default.HowToVote
        VoteUiState.Status.CLOSED -> Icons.Default.Close
    }
}

@Composable
private fun VoteUiState.Status.toLabel(): String {
    return when (this) {
        VoteUiState.Status.IN_PROGRESS -> stringResource(id = R.string.inprogress)
        VoteUiState.Status.CLOSED -> stringResource(id = R.string.closed)
    }
}

@Composable
private fun VoteUiState.Status.toBackgroundColor(): Color {
    return when (this) {
        VoteUiState.Status.IN_PROGRESS -> MaterialTheme.colorScheme.primaryContainer
        VoteUiState.Status.CLOSED -> MaterialTheme.colorScheme.errorContainer
    }
}

@Composable
private fun VoteUiState.Status.toTextColor(): Color {
    return when (this) {
        VoteUiState.Status.IN_PROGRESS -> MaterialTheme.colorScheme.onPrimaryContainer
        VoteUiState.Status.CLOSED -> MaterialTheme.colorScheme.onErrorContainer
    }
}

@Composable
private fun VoterCount(
    modifier: Modifier = Modifier,
    voterCount: String
) {
    IconWithText(
        modifier = modifier,
        imageVector = Icons.Default.People,
        contentDescription = stringResource(id = R.string.voter_count),
        text = voterCount,
        textColor = MaterialTheme.colorScheme.onTertiaryContainer,
        backgroundColor = MaterialTheme.colorScheme.tertiaryContainer
    )
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        modifier = modifier,
        text = text,
    )
}

@Composable
private fun BallotItems(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    canMultipleChoice: Boolean,
    ballotItems: ImmutableList<BallotItemUiState>,
    onCheckedChange: (BallotItemUiState) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        ballotItems.forEach { ballotItem ->
            key(ballotItem.name) {
                BallotItem(
                    modifier = Modifier.fillMaxWidth(),
                    text = ballotItem.name,
                    isChecked = ballotItem.isVoted,
                    enabled = enabled,
                    canMultipleChoice = canMultipleChoice,
                    onCheckedChange = {
                        onCheckedChange(ballotItem)
                    }
                )
            }
        }
    }
}

@Composable
private fun BallotItem(
    modifier: Modifier = Modifier,
    text: String,
    isChecked: Boolean,
    enabled: Boolean,
    canMultipleChoice: Boolean,
    onCheckedChange: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (canMultipleChoice) {
            true -> Checkbox(
                enabled = enabled,
                checked = isChecked,
                onCheckedChange = {
                    onCheckedChange()
                }
            )

            else -> RadioButton(
                enabled = enabled,
                selected = isChecked,
                onClick = {
                    onCheckedChange()
                }
            )
        }
        Text(
            modifier = Modifier.weight(1f),
            text = text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
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
private fun Statistics(
    modifier: Modifier = Modifier,
    ballotItems: ImmutableList<BallotItemUiState>,
) {
    val modelProducer = remember {
        CartesianChartModelProducer()
    }
    LaunchedEffect(ballotItems) {
        modelProducer.runTransaction {
            columnSeries {
                series(
                    ballotItems.map {
                        it.votingCount
                    }
                )
            }
        }
    }
    CartesianChartHost(
        modifier = modifier,
        chart = rememberCartesianChart(
            rememberColumnCartesianLayer(
                ColumnCartesianLayer.ColumnProvider.series(
                    rememberLineComponent(
                        color = MaterialTheme.colorScheme.inversePrimary,
                        thickness = Defaults.COLUMN_WIDTH.dp,
                        shape = Shape.rounded(topLeft = 4.dp, topRight = 4.dp)
                    ),
                ),
            ),
            startAxis = rememberStartAxis(
                label = TextComponent(
                    margins = Dimensions.of(end = 8.dp)
                ),
                tick = null,
                valueFormatter = remember {
                    { value, _, _ ->
                        val toInt = value.toInt()
                        if (value > toInt && value < toInt + 1) {
                            ""
                        } else {
                            toInt.toString()
                        }
                    }
                }
            ),
            bottomAxis = rememberBottomAxis(
                tick = null,
                guideline = null,
                valueFormatter = remember {
                    { value, _, _ ->
                        ballotItems[value.toInt()].name
                    }
                }
            ),
            marker = DefaultCartesianMarker(
                label = TextComponent(
                    textAlignment = Layout.Alignment.ALIGN_OPPOSITE
                ),
                labelPosition = DefaultCartesianMarker.LabelPosition.AbovePoint
            )
        ),
        modelProducer = modelProducer
    )
}

@Composable
@Preview(showBackground = true)
internal fun LoadedPreview(
    @PreviewParameter(VoteDetailLoadedUiStatePreviewParameterProvider::class)
    uiState: VoteDetailUiState.Loaded
) {
    VoteChainTheme {
        VoteDetailScreen.Loaded(
            uiState = uiState,
            onBallotItemCheckedChange = {},
            onSubmitClick = {},
            onConsumedToastMessage = {}
        )
    }
}