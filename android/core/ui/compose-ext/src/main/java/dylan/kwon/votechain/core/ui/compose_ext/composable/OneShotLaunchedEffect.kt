package dylan.kwon.votechain.core.ui.compose_ext.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope

@Composable
fun OneShotLaunchedEffect(
    vararg keys: Any?,
    block: suspend CoroutineScope.() -> Unit
) {
    var isLaunched by rememberSaveable(*keys) {
        mutableStateOf(false)
    }
    if (!isLaunched) LaunchedEffect(Unit) {
        block()
        isLaunched = true
    }
}