package dev.luisamartins.medreminder.ui.screens.addmedication

import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.luisamartins.medreminder.model.DosageUnit
import dev.luisamartins.medreminder.model.MedicationType
import dev.luisamartins.medreminder.ui.components.DosageUnitDropdownMenu
import dev.luisamartins.medreminder.ui.components.SelectMedicationType
import dev.luisamartins.medreminder.ui.theme.MedReminderTheme

@Composable
fun AddMedicationContent(
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("") }
    var dosage by remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
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
            Row(
                modifier = Modifier.padding(24.dp),
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