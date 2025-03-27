package dev.luisamartins.medreminder.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
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
    HomeContent(
        currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        medications = viewModel.state.medications,
        onAddClick = {
            navController.navigate(AddMedicationScreen)
        },
        onItemClick = { medication: HomeMedicationItemUiState ->
            viewModel.checkMedication(medication)
        },
        modifier = modifier
    )
}

@Serializable
object HomeScreen