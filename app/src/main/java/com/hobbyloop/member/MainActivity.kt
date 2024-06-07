package com.hobbyloop.member

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hobbyloop.member.navigationbar.NAVIGATION_BAR_HOST_ROUTE
import com.hobbyloop.member.root.RootHost
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import theme.HobbyloopTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    private var uiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSplashScreen()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.onEach { uiState = it }.collect()
            }
        }

        setContent {
            HobbyloopTheme {
                when (uiState) {
                    MainActivityUiState.Loading -> Unit
                    is MainActivityUiState.Success -> RenderMainContent(uiState as MainActivityUiState.Success)
                }
            }
        }
    }

    @Composable
    private fun RenderMainContent(uiState: MainActivityUiState.Success) {
        val startDestination =
            if (uiState.userData.jwt.isEmpty()) {
                NAVIGATION_BAR_HOST_ROUTE
                // LOGIN_GRAPH_ROUTE
            } else {
                NAVIGATION_BAR_HOST_ROUTE
            }

        RootHost(startDestination = startDestination)
    }

    private fun setupSplashScreen() {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                MainActivityUiState.Loading -> true
                is MainActivityUiState.Success -> false
            }
        }
    }
}
