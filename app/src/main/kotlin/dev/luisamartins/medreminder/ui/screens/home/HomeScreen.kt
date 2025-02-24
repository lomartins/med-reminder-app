package dev.luisamartins.medreminder.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import dev.luisamartins.medreminder.model.DosageUnit
import dev.luisamartins.medreminder.model.Medication
import dev.luisamartins.medreminder.model.MedicationType
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
    HomeContent(
        currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
        medications = listOf(
            Medication(
                id = 1,
                name = "Omega 3",
                dosage = 1,
                remainingTimeInDays = 7,
                dosageUnit = DosageUnit.Pills,
                type = MedicationType.PILL,
                time = "8:00",
            ),
            Medication(
                id = 2,
                name = "Vitamina D",
                dosage = 25,
                remainingTimeInDays = -1,
                dosageUnit = DosageUnit.ML,
                type = MedicationType.SYRUP,
                time = "8:00",
            ),
            Medication(
                id = 3,
                name = "Vitamina C",
                dosage = 1,
                remainingTimeInDays = 7,
                dosageUnit = DosageUnit.Pills,
                type = MedicationType.PILL,
                time = "12:00",
            ),
            Medication(
                id = 4,
                name = "Vitamina D",
                dosage = 25,
                remainingTimeInDays = -1,
                dosageUnit = DosageUnit.ML,
                type = MedicationType.SYRUP,
                time = "12:00",
            ),
            Medication(
                id = 5,
                name = "Vitamina C",
                dosage = 1,
                remainingTimeInDays = 7,
                dosageUnit = DosageUnit.Pills,
                type = MedicationType.PILL,
                time = "16:00",
            ),
            Medication(
                id = 6,
                name = "Vitamina D",
                dosage = 25,
                remainingTimeInDays = -1,
                dosageUnit = DosageUnit.ML,
                type = MedicationType.SYRUP,
                time = "16:00",
            ),
        ),
        modifier = modifier
    )
}

@Serializable
object HomeScreen