package dylan.kwon.votechain.ui.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import dagger.hilt.android.AndroidEntryPoint
import dylan.kwon.votechain.R
import dylan.kwon.votechain.ui.VoteChainApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_VoteChain)

        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            VoteChainApp()
        }
        splashScreen.awaitSetup()
    }

    private fun SplashScreen.awaitSetup() {
        setKeepOnScreenCondition {
            when(val uiState = viewModel.uiState.value) {
                is MainUiState.NoSetup -> !uiState.isAuth
                else -> false
            }
        }
    }
}