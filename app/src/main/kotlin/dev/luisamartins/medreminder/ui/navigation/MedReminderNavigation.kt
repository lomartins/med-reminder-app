package dev.luisamartins.medreminder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.luisamartins.medreminder.ui.screens.addmedication.AddMedicationScreen
import dev.luisamartins.medreminder.ui.screens.home.HomeScreen

@Composable
fun MedReminderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HomeScreen) {
        composable<HomeScreen> {
            HomeScreen(
                navController = navController
            )
        }
        composable<AddMedicationScreen> {
            AddMedicationScreen(
                navController = navController
            )
        }
    }
}