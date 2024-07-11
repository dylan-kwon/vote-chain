package dylan.kwon.votechain.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun MainRoute() {
    MainScreen()
}

@Composable
internal fun MainScreen() {
    Scaffold { paddingValue ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "MAIN"
            )
        }
    }
}