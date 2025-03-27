package dev.luisamartins.medreminder.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.luisamartins.medreminder.domain.model.DosageUnit
import dev.luisamartins.medreminder.ui.theme.MedReminderTheme

@Composable
fun DosageUnitDropdownMenu(
    selectedUnit: DosageUnit,
    onUnitSelected: (DosageUnit) -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedText = remember { mutableStateOf(selectedUnit.name) }
    val expanded = remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraSmall)
            .background(MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { expanded.value = true }
        ) {
            Text(
                text = selectedText.value,
                modifier = Modifier
                    .padding(16.dp)
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            DosageUnit.entries.forEach { unit ->
                DropdownMenuItem(
                    text = { Text(unit.name) },
                    onClick = {
                        selectedText.value = unit.name
                        onUnitSelected(unit)
                        expanded.value = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun DosageUnitDropdownMenuPreview() {
    val selectedUnit = remember { mutableStateOf(DosageUnit.MG) }
    MedReminderTheme {
        DosageUnitDropdownMenu(
            selectedUnit = selectedUnit.value,
            onUnitSelected = { selectedUnit.value = it }
        )
    }
}