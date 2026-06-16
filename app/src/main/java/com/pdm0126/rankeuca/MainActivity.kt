package com.pdm0126.rankeuca

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.pdm0126.rankeuca.data.AppProvider
import com.pdm0126.rankeuca.screens.HomeScreen
import com.pdm0126.rankeuca.screens.OptionScreen
import com.pdm0126.rankeuca.screens.ResultScreen
import com.pdm0126.rankeuca.ui.theme.RankeUCATheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RankeUCATheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var currentScreen by remember { mutableStateOf("options") }

                    when(currentScreen) {
                        "options" -> {
                            OptionScreen(
                                onNavigateTovoting = {
                                    currentScreen = "home"
                                }
                            )
                        }
                        "home" ->{
                            HomeScreen(
                                onNaigateToResults = {
                                    currentScreen = "results"
                                }
                            )
                        }
                        "results" -> {
                            ResultScreen(
                                onNavigateBack = {
                                    currentScreen = "home"
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

class Taller3RankeUca : Application() {
    val appProvider by lazy { AppProvider(this) }
}





