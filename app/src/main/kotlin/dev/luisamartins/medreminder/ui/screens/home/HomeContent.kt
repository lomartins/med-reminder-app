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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.luisamartins.medreminder.R
import dev.luisamartins.medreminder.ui.components.MedicationListForTime
import dev.luisamartins.medreminder.ui.components.WeekDisplay
import dev.luisamartins.medreminder.ui.theme.MedReminderTheme
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    currentDate: LocalDate,
    medications: List<HomeMedicationItemUiState>,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
    onItemClick: (HomeMedicationItemUiState) -> Unit = {}
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var currentSelectedMedication by remember { mutableStateOf<HomeMedicationItemUiState?>(null) }

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
                date = currentDate, modifier = Modifier
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
                        MedicationListForTime(time = time,
                            medications = medications,
                            onMedicationClick = {
                                currentSelectedMedication = it
                                showBottomSheet = true
                                scope.launch { sheetState.show() }
                            })
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = onAddClick, modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(Icons.Filled.Add, stringResource(R.string.add_med))
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                }, sheetState = sheetState
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        stringResource(R.string.check_med_message),
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.padding(16.dp))
                    Button(
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                                currentSelectedMedication?.let { onItemClick(it) }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            stringResource(R.string.check_med_button),
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    Button(
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            stringResource(R.string.check_med_abort),
                            style = MaterialTheme.typography.headlineMedium,
                        )
                    }
                }
            }
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
                HomeMedicationItemUiState(
                    name = "Omega 3",
                    time = "8:00",
                    type = MedicationTypeUiState.PILL,
                    typeDescription = "Pill",
                    dosage = "1 Pill",
                    remainingTime = null,
                    isOverdue = false,
                    isTaken = false
                ),
                HomeMedicationItemUiState(
                    name = "Vitamina D",
                    time = "8:00",
                    type = MedicationTypeUiState.PILL,
                    typeDescription = "Syrup",
                    dosage = "25 ML",
                    remainingTime = null,
                    isOverdue = false,
                    isTaken = false
                ),
                HomeMedicationItemUiState(
                    name = "Vitamina C",
                    time = "12:00",
                    type = MedicationTypeUiState.PILL,
                    typeDescription = "Pill",
                    dosage = "1 Pill",
                    remainingTime = null,
                    isOverdue = false,
                    isTaken = false
                ),
                HomeMedicationItemUiState(
                    name = "Vitamina D",
                    time = "12:00",
                    type = MedicationTypeUiState.PILL,
                    typeDescription = "Syrup",
                    dosage = "25 ML",
                    remainingTime = null,
                    isOverdue = false,
                    isTaken = false
                ),
                HomeMedicationItemUiState(
                    name = "Vitamina C",
                    time = "16:00",
                    type = MedicationTypeUiState.PILL,
                    typeDescription = "Pill",
                    dosage = "1 Pill",
                    remainingTime = null,
                    isOverdue = false,
                    isTaken = false
                ),
                HomeMedicationItemUiState(
                    name = "Vitamina D",
                    time = "16:00",
                    type = MedicationTypeUiState.PILL,
                    typeDescription = "Syrup",
                    dosage = "25 ML",
                    remainingTime = null,
                    isOverdue = false,
                    isTaken = false
                ),
            )
        )
    }
}
