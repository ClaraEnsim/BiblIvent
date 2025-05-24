package com.example.biblivent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.biblivent.ui.theme.BiblIventTheme
import com.example.biblivent.ui.theme.screens.AccueilScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BiblIventTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "accueil"
                    ) {
                        composable("accueil") {
                            AccueilScreen(
                                onNavigateToDetail = {
                                    // navController.navigate("detail")
                                }
                            )
                        }

                        // composable("detail") { DetailScreen() }
                    }
                }
            }
        }
    }
}
