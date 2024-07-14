package dylan.kwon.votechain.core.ui.navigation.result

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.StateFlow

fun <T> NavHostController.setResult(key: String, value: T?) {
    previousBackStackEntry?.savedStateHandle?.set(key, value)
}

fun <T> NavBackStackEntry.receiveResult(key: String, initialValue: T): StateFlow<T> =
    savedStateHandle.getStateFlow(key, initialValue)

@Composable
fun <T> NavBackStackEntry.receiveResultWithLifecycle(key: String, initialValue: T): State<T> =
    receiveResult(key, initialValue).collectAsStateWithLifecycle(lifecycle)