package dev.luisamartins.medreminder.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.luisamartins.medreminder.R
import dev.luisamartins.medreminder.model.DosageUnit
import dev.luisamartins.medreminder.model.Medication
import dev.luisamartins.medreminder.model.MedicationType
import dev.luisamartins.medreminder.ui.components.MedicationListForTime
import dev.luisamartins.medreminder.ui.components.WeekDisplay
import dev.luisamartins.medreminder.ui.theme.MedReminderTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun HomeContent(
    currentDate: LocalDate,
    medications: List<Medication>,
    onAddClick: () -> Unit = {},
    onItemClick: (Medication) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
            .padding(24.dp)
    ) {
        Column {
            Text(
                text = stringResource(R.string.planning_title),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
            )
            WeekDisplay(
                date = currentDate,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )

            Spacer(modifier = Modifier.padding(16.dp))
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 64.dp)
            ) {
                medications.groupBy { it.time }.map { (time, medications) ->
                    item {
                        MedicationListForTime(
                            time = time,
                            medications = medications,
                            onMedicationClick = onItemClick
                        )
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = onAddClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }
    }
}

@Preview
@Composable
private fun HomeContentPreview() {
    MedReminderTheme {
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
            )
        )
    }
}