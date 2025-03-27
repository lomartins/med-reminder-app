package dev.luisamartins.medreminder.ui.screens.addmedication

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddMedicationScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    addMedicationViewModel: AddMedicationViewModel = koinViewModel()
) {
    AddMedicationContent(
        onCreateClick = { medicationForm ->
            addMedicationViewModel.addMedication(medicationForm)
            navController.popBackStack()
        },
        modifier = modifier
    )
}

@Serializable
object AddMedicationScreen
