package dylan.kwon.votechain.core.ui.design_system.theme.composable.image

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import dylan.kwon.votechain.core.ui.design_system.R


@Composable
fun VoteChainImage(
    modifier: Modifier = Modifier,
    model: Any?,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Inside,
) {
    SubcomposeAsyncImage(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .then(modifier),
        model = model,
        contentDescription = contentDescription,
        contentScale = ContentScale.Inside,
        loading = {
            Placeholder(
                imageVector = Icons.Default.Image,
                contentDescription = stringResource(id = R.string.loading),
            )
        },
        error = {
            Placeholder(
                imageVector = Icons.Default.BrokenImage,
                contentDescription = stringResource(id = R.string.error),
            )
        },
        success = {
            SubcomposeAsyncImageContent(
                contentScale = contentScale
            )
        },
    )
}

@Composable
private fun Placeholder(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    contentDescription: String
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = imageVector,
            tint = MaterialTheme.colorScheme.surfaceDim,
            contentDescription = contentDescription,
        )
    }
}
