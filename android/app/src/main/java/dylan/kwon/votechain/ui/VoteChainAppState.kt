package dylan.kwon.votechain.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberAppState(
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
) = VoteChainAppState(
    coroutineScope = coroutineScope,
    navController = navController,
)

@Stable
class VoteChainAppState(
    val coroutineScope: CoroutineScope,
    val navController: NavHostController
)
