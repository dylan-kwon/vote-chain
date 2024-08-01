package dylan.kwon.votechain.core.ui.navigation.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavBackStackEntry
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach

@Composable
fun <T> NavBackStackEntry.receiveResultAsStateWithLifecycle(
    key: String,
    initialValue: T
) = receiveResult(key, initialValue)
    .collectAsStateWithLifecycle()


@Composable
fun <T> NavBackStackEntry.ReceiveResultEffect(
    resultKey: String,
    vararg keys: Any?,
    collector: (T) -> Unit
) {
    LaunchedEffect(
        this,
        lifecycleScope,
        resultKey,
        *keys,
        collector
    ) {
        receiveResult<T>(resultKey)
            .filterNotNull()
            .onEach {
                savedStateHandle.remove<T>(resultKey)
            }
            .flowWithLifecycle(
                this@ReceiveResultEffect.lifecycle
            )
            .collect(collector)
    }
}