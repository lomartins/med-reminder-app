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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.luisamartins.medreminder.R
import dev.luisamartins.medreminder.domain.model.DosageUnit
import dev.luisamartins.medreminder.domain.model.MedicationType
import dev.luisamartins.medreminder.ui.components.DosageUnitDropdownMenu
import dev.luisamartins.medreminder.ui.components.SelectMedicationType
import dev.luisamartins.medreminder.ui.screens.addmedication.model.MedicationForm
import dev.luisamartins.medreminder.ui.theme.MedReminderTheme
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMedicationContent(
    onCreateClick: (MedicationForm) -> Unit,
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("") }
    var dosage by remember { mutableStateOf("") }
    val times: MutableState<List<TimePickerState>> = remember { mutableStateOf(listOf()) }

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
                text = stringResource(R.string.add_med_title),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(24.dp)
            )
            TextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(stringResource(R.string.med_name)) },
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
                        label = { Text(stringResource(R.string.med_dosage)) },
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

                var numberOfTimes: Int by remember { mutableIntStateOf(1) }
                val currentTime = Calendar.getInstance()
                if (numberOfTimes > times.value.size) {
                    for (i in 0 until numberOfTimes - times.value.size) {
                        times.value = times.value + rememberTimePickerState(
                            initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
                            initialMinute = currentTime.get(Calendar.MINUTE),
                            is24Hour = true
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = stringResource(R.string.med_time),
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
                    onClick = {
                        val form = MedicationForm(
                            title = title,
                            dosage = dosage,
                            dosageUnit = DosageUnit.ML,
                            type = MedicationType.PILL,
                            times = times.value.map { it.formatTime() },
                        )
                        onCreateClick(form)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .padding(24.dp),
                ) {
                    Text(
                        text = stringResource(R.string.med_add_button),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private fun TimePickerState.formatTime(): String {
    if (is24hour) {
        return String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
    } else {
        // TODO: implement 12 hour format
        throw NotImplementedError("12 hour format not implemented")
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
                if (selectedTimes.size > 1) {
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
        AddMedicationContent(
            onCreateClick = {}
        )
    }
}