package dev.luisamartins.medreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.luisamartins.medreminder.di.appModule
import dev.luisamartins.medreminder.ui.screens.addmedication.AddMedicationScreen
import dev.luisamartins.medreminder.ui.screens.home.HomeScreen
import dev.luisamartins.medreminder.ui.theme.MedReminderTheme
import org.koin.compose.KoinApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SetupKoin {
                MedReminderTheme {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = HomeScreen) {
                        composable<HomeScreen> {
                            HomeScreen(
                                navController = navController
                            )
                        }
                        composable<AddMedicationScreen> {
                            AddMedicationScreen()
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun SetupKoin(content: @Composable () -> Unit) {
        KoinApplication(
            application = {
                modules(appModule)
            },
            content = content
        )
    }
}