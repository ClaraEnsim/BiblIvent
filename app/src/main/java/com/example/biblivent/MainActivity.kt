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
import com.example.biblivent.ui.theme.screens.*

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
                        // Accueil
                        composable("accueil") {
                            AccueilScreen(
                                onNavigateToDetail = {
                                    navController.navigate("depot")
                                }
                            )
                        }

                        // Dépôt
                        composable("depot") {
                            DepotScreen(
                                onBack = { navController.navigate("accueil") },
                                onValidate = { navController.navigate("cover") },
                                onNavigateToDepot = { navController.navigate("depot") },
                                onNavigateToCover = { navController.navigate("cover") },
                                onNavigateToDetails = { navController.navigate("detail") },
                                onNavigateToEditors = { navController.navigate("editors") }
                            )
                        }

                        // Couverture
                        composable("cover") {
                            CoverScreen(
                                onBack = { navController.navigate("accueil") },
                                onValidate = { navController.navigate("detail") },
                                onNavigateToDepot = { navController.navigate("depot") },
                                onNavigateToCover = { navController.navigate("cover") },
                                onNavigateToDetails = { navController.navigate("detail") },
                                onNavigateToEditors = { navController.navigate("editors") }
                            )
                        }

                        // Caractéristiques
                        composable("detail") {
                            DetailScreen(
                                onBack = { navController.navigate("accueil") },
                                onValidate = { navController.navigate("editors") },
                                onNavigateToDepot = { navController.navigate("depot") },
                                onNavigateToCover = { navController.navigate("cover") },
                                onNavigateToDetails = { navController.navigate("detail") },
                                onNavigateToEditors = { navController.navigate("editors") }
                            )
                        }

                        // Maisons d'édition intéressées
                        composable("editors") {
                            EditorScreen(
                                onBack = { navController.navigate("accueil") },
                                onValidate = {navController.navigate("update")},
                                onNavigateToDepot = { navController.navigate("depot") },
                                onNavigateToCover = { navController.navigate("cover") },
                                onNavigateToDetails = { navController.navigate("detail") },
                                onNavigateToEditors = { navController.navigate("editors") }
                            )
                        }

                        // Ajoute ici la page finale si nécessaire
                        composable("update") {
                            UpdateScreen(
                                onNavigateToDetail = { navController.navigate("depot")}
                            )
                        }
                    }
                }
            }
        }
    }
}
