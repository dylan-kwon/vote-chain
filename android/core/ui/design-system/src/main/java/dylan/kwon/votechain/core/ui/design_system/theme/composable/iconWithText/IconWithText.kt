package dylan.kwon.votechain.core.ui.design_system.theme.composable.iconWithText

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dylan.kwon.votechain.core.ui.design_system.theme.VoteChainTheme

@Composable
fun IconWithText(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String?,
    text: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(100))
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription
        )
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(
                color = textColor
            )
        )
    }
}

@Preview
@Composable
private fun Preview() {
    VoteChainTheme {
        IconWithText(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            text = "Plus",
        )
    }
}