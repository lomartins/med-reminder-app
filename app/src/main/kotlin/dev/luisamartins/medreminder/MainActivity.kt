package dev.luisamartins.medreminder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.luisamartins.medreminder.ui.navigation.MedReminderNavigation
import dev.luisamartins.medreminder.ui.theme.MedReminderTheme
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MedReminderTheme {
                KoinContext {
                    MedReminderNavigation()
                }
            }
        }
    }

}