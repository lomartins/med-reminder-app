package dev.luisamartins.medreminder.ui.screens.addmedication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.luisamartins.medreminder.model.DosageUnit
import dev.luisamartins.medreminder.model.MedicationType
import dev.luisamartins.medreminder.ui.components.DosageUnitDropdownMenu
import dev.luisamartins.medreminder.ui.components.SelectMedicationType
import dev.luisamartins.medreminder.ui.theme.MedReminderTheme
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMedicationContent(
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("") }
    var dosage by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
            .focusRequester(
                focusRequester = focusRequester
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            Text(
                text = "Adicionar Procedimento",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(24.dp)
            )
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            )
            SelectMedicationType(
                availableTypes = MedicationType.entries,
                selectedType = remember { mutableStateOf(MedicationType.PILL) }
            )
            Column(
                modifier = Modifier.padding(24.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = dosage,
                        onValueChange = { dosage = it },
                        label = { Text("Dosagem") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    DosageUnitDropdownMenu(
                        selectedUnit = DosageUnit.ML,
                        onUnitSelected = {},
                        modifier = Modifier
                    )
                }
                Spacer(modifier = Modifier.padding(16.dp))

                val currentTime = Calendar.getInstance()
                val initialPickerState = rememberTimePickerState(
                    initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
                    initialMinute = currentTime.get(Calendar.MINUTE),
                    is24Hour = true
                )
                val times = remember { mutableStateOf(listOf(initialPickerState)) }
                var numberOfTimes: Int by remember { mutableIntStateOf(1) }
                if (numberOfTimes > times.value.size) {
                    times.value = times.value + rememberTimePickerState(
                        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
                        initialMinute = currentTime.get(Calendar.MINUTE),
                        is24Hour = true
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    Text(
                        text = "Horário",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    // plus button
                    IconButton(
                        onClick = {
                            numberOfTimes += 1
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                TimePicker(
                    selectedTimes = times.value,
                    onRemoveClick = { time ->
                        times.value = times.value.filter { it != time }
                        numberOfTimes -= 1
                    }
                )

                Button(
                    onClick = {},
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(24.dp),

                ) {
                    Text(
                        text = "Adicionar",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TimePicker(
    selectedTimes: List<TimePickerState>,
    onRemoveClick: (TimePickerState) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        selectedTimes.forEach { time ->
            Row {
                TimeInput(
                    state = time,
                )
                if(selectedTimes.size > 1) {
                    IconButton(
                        onClick = { onRemoveClick(time) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun AddMedicationContentPreview() {
    MedReminderTheme {
        AddMedicationContent()
    }
}