package dylan.kwon.votechain.core.ui.design_system.theme.composable.image

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage


@Composable
fun VoteChainImage(
    modifier: Modifier = Modifier,
    model: Any?,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Fit,
) {
    AsyncImage(
        modifier = modifier,
        model = model,
        contentDescription = contentDescription,
        contentScale = contentScale,
        placeholder = rememberVectorPainter(Icons.Default.Image),
        fallback = rememberVectorPainter(Icons.Default.BrokenImage),
        error = rememberVectorPainter(Icons.Default.BrokenImage),
    )
}
