package dylan.kwon.votechain.feature.auth.ui.composable.numPad

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.cheonjaeung.compose.grid.GridScope
import com.cheonjaeung.compose.grid.HorizontalGrid
import com.cheonjaeung.compose.grid.SimpleGridCells
import com.cheonjaeung.compose.grid.VerticalGrid
import dylan.kwon.votechain.core.ui.design_system.theme.VoteChainTheme
import dylan.kwon.votechain.feature.auth.R
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun NumPad(
    modifier: Modifier = Modifier,
    numPadState: NumPadState = rememberNumPadState(),
    onNumberClick: (number: Int) -> Unit,
    onDeleteClick: () -> Unit
) {
    val data by numPadState.data

    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            NumPadPortrait(
                modifier = modifier,
                data = data,
                onNumberClick = {
                    onNumberClick(it.value)
                },
                onDeleteClick = onDeleteClick
            )
        }

        else -> {
            NumPadLandscape(
                modifier = modifier,
                data = data,
                onNumberClick = {
                    onNumberClick(it.value)
                },
                onDeleteClick = onDeleteClick
            )
        }
    }
}


@Composable
private fun NumPadPortrait(
    modifier: Modifier = Modifier,
    data: ImmutableList<NumPadState.Data>,
    onNumberClick: (NumPadState.Data.Number) -> Unit,
    onDeleteClick: () -> Unit
) {
    VerticalGrid(
        modifier = modifier,
        columns = SimpleGridCells.Fixed(4),
    ) {
        NumPadItems(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            data = data,
            onNumberClick = onNumberClick,
            onDeleteClick = onDeleteClick,
        )
    }
}

@Composable
private fun NumPadLandscape(
    modifier: Modifier = Modifier,
    data: ImmutableList<NumPadState.Data>,
    onNumberClick: (NumPadState.Data.Number) -> Unit,
    onDeleteClick: () -> Unit
) {
    HorizontalGrid(
        modifier = modifier,
        rows = SimpleGridCells.Fixed(4),
    ) {
        NumPadItems(
            modifier = Modifier
                .fillMaxHeight()
                .aspectRatio(1f),
            data = data,
            onNumberClick = onNumberClick,
            onDeleteClick = onDeleteClick,
        )
    }
}

@Composable
@Suppress("UnusedReceiverParameter")
private fun GridScope.NumPadItems(
    modifier: Modifier,
    data: ImmutableList<NumPadState.Data>,
    onNumberClick: (NumPadState.Data.Number) -> Unit,
    onDeleteClick: () -> Unit
) {
    data.forEach { element ->
        when (element) {
            is NumPadState.Data.Number -> {
                NumberButton(
                    modifier = modifier,
                    number = element,
                    onClick = {
                        onNumberClick(element)
                    },
                )
            }

            is NumPadState.Data.Empty -> {
                Empty(modifier = modifier)
            }

            is NumPadState.Data.Delete -> {
                DeleteButton(
                    modifier = modifier,
                    onClick = onDeleteClick
                )
            }
        }
    }
}

@Composable
private fun NumberButton(
    modifier: Modifier = Modifier,
    number: NumPadState.Data.Number,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number.value.toString(),
        )
    }
}

@Composable
private fun DeleteButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            Icons.AutoMirrored.Default.Backspace,
            contentDescription = stringResource(id = R.string.backspace)
        )
    }
}

@Composable
private fun Empty(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

    }
}

@Composable
@Preview(showBackground = true)
private fun Preview() {
    VoteChainTheme {
        NumPad(
            onNumberClick = {},
            onDeleteClick = {}
        )
    }
}