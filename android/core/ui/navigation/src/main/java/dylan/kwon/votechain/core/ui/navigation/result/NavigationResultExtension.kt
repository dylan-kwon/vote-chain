package dylan.kwon.votechain.core.ui.navigation.result

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

fun <T> NavHostController.setResult(key: String, value: T?) {
    previousBackStackEntry?.savedStateHandle?.set(key, value)
}

fun <T> NavBackStackEntry.receiveResult(
    key: String,
    initialValue: T? = null,
) = savedStateHandle.getStateFlow(key, initialValue)
