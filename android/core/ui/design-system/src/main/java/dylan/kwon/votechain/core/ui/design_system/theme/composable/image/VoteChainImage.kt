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
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.compose.SubcomposeAsyncImageScope
import dylan.kwon.votechain.core.ui.design_system.R


@Composable
fun VoteChainImage(
    modifier: Modifier = Modifier,
    model: Any?,
    contentDescription: String?,
    contentScale: ContentScale = ContentScale.Inside,
    loading: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Loading) -> Unit)? = {
        Placeholder(
            imageVector = Icons.Default.Image,
            contentDescription = stringResource(id = R.string.loading),
        )
    },
    error: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Error) -> Unit)? = {
        Placeholder(
            imageVector = Icons.Default.BrokenImage,
            contentDescription = stringResource(id = R.string.error),
        )
    },
    success: @Composable (SubcomposeAsyncImageScope.(AsyncImagePainter.State.Success) -> Unit)? = {
        SubcomposeAsyncImageContent(
            contentScale = contentScale
        )
    }
) {
    SubcomposeAsyncImage(
        modifier = modifier.background(MaterialTheme.colorScheme.surfaceContainerLow),
        model = model,
        contentDescription = contentDescription,
        contentScale = ContentScale.Inside,
        loading = loading,
        error = error,
        success = success,
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
