package dylan.kwon.votechain.core.ui.navigation.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavBackStackEntry
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun <T> NavBackStackEntry.receiveResultAsStateWithLifecycle(
    key: String,
    initialValue: T
) = receiveResult(key, initialValue)
    .collectAsStateWithLifecycle()


@Composable
fun <T> NavBackStackEntry.ReceiveResultEffect(
    key: String,
    collector: (T) -> Unit
) {
    LaunchedEffect(
        this,
        lifecycleScope,
        key,
        collector
    ) {
        receiveResult<T>(key)
            .filterNotNull()
            .onEach {
                savedStateHandle.remove<T>(key)
            }
            .onEach(collector)
            .launchIn(this)
    }
}