package dev.luisamartins.medreminder.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.luisamartins.medreminder.model.DosageUnit
import dev.luisamartins.medreminder.model.Medication
import dev.luisamartins.medreminder.model.MedicationType
import dev.luisamartins.medreminder.ui.screens.addmedication.AddMedicationScreen
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadMedications()
    }
    val state = viewModel.state
    HomeContent(
        currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        medications = state.medications,
        onAddClick = {
            navController.navigate(AddMedicationScreen)
        },
        onItemClick = { medication: Medication ->
            viewModel.markMedicationAsChecked(medication)
        },
        modifier = modifier
    )
}

@Serializable
object HomeScreen