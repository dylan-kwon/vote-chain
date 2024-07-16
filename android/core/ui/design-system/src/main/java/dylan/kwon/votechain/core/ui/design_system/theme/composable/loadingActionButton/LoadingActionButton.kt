package dylan.kwon.votechain.core.ui.design_system.theme.composable.loadingActionButton

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingActionButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = {
            if (!isLoading) {
                onClick()
            }
        }
    ) {
        when (isLoading) {
            true -> CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.dp
            )

            else -> icon()
        }
    }
}