package dylan.kwon.votechain.feature.auth.ui.navigation

import androidx.navigation.NavGraphBuilder
import dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.SimplePasswordNavigationResult
import dylan.kwon.votechain.feature.auth.ui.screen.simplePassword.attachSimplePasswordScreen

fun NavGraphBuilder.attachAuthNavigation(
    onSimplePasswordResult: (SimplePasswordNavigationResult) -> Unit
) {
    attachSimplePasswordScreen(
        onResult = onSimplePasswordResult
    )
}