package dev.luisamartins.medreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.luisamartins.medreminder.ui.screens.home.HomeScreen
import dev.luisamartins.medreminder.ui.theme.MedReminderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MedReminderTheme {
                NavHost(navController = navController, startDestination = HomeScreen) {
                    composable<HomeScreen> {
                        HomeScreen()
                    }
                }
            }
        }
    }
}