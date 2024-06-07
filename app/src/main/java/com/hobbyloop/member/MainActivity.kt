package com.hobbyloop.member

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.hobbyloop.member.root.RootHost
import dagger.hilt.android.AndroidEntryPoint
import theme.HobbyloopTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HobbyloopTheme {
                RootHost()
            }
        }
    }
}
