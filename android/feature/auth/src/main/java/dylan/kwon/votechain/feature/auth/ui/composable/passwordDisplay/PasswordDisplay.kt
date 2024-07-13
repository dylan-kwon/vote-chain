package dylan.kwon.votechain.feature.auth.ui.composable.passwordDisplay

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp

@Composable
internal fun PasswordDisplay(
    modifier: Modifier = Modifier,
    maximumSize: Int,
    inputtedSize: Int,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(maximumSize) {
            Column(
                modifier = Modifier.width(IntrinsicSize.Min)
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .alpha(
                            when {
                                it < inputtedSize -> 1f
                                else -> 0f
                            }
                        ),
                    text = "*",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.Both
                        )
                    )
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(MaterialTheme.colorScheme.outline)
                )
            }
        }
    }
}
