package dylan.kwon.votechain.core.ui.compose_ext

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
    var isLaunched by rememberSaveable {
        mutableStateOf(false)
    }
    if (!isLaunched) LaunchedEffect(keys) {
        block()
        isLaunched = true
    }
}