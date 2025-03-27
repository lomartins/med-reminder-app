package dev.luisamartins.medreminder.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Droplets
import com.composables.icons.lucide.FlaskConical
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Pill
import com.composables.icons.lucide.Syringe
import com.composables.icons.lucide.Tablets
import dev.luisamartins.medreminder.domain.model.MedicationType
import dev.luisamartins.medreminder.ui.theme.MedReminderTheme

@Composable
fun SelectMedicationType(
    availableTypes: List<MedicationType>,
    selectedType: MutableState<MedicationType>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(availableTypes) { type ->
            val (name: String, icon: ImageVector) = when (type) {
                MedicationType.PILL -> {
                    "Pill" to Lucide.Pill
                }

                MedicationType.TABLET -> {
                    "Tablet" to Lucide.Tablets
                }

                MedicationType.INJECTION -> {
                    "Injection" to Lucide.Syringe
                }

                MedicationType.SYRUP -> {
                    "Syrup" to Lucide.FlaskConical
                }

                MedicationType.DROPS -> {
                    "Drops" to Lucide.Droplets
                }

                else -> {
                    "Pill" to Lucide.Pill
                }
            }
            MedicationTypeCircle(
                name = name,
                icon = icon,
                enabled = type == selectedType.value,
                onCLick = {
                    selectedType.value = type
                }
            )
        }
    }
}

@Preview
@Composable
private fun SelectMedicationTypePreview() {
    MedReminderTheme {
        val selectedType = remember { mutableStateOf(MedicationType.PILL) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            SelectMedicationType(
                availableTypes = MedicationType.values().toList(),
                selectedType = selectedType
            )
        }
    }
}