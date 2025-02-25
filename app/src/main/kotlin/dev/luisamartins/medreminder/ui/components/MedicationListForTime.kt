package dev.luisamartins.medreminder.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Droplets
import com.composables.icons.lucide.FlaskConical
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Pill
import com.composables.icons.lucide.Syringe
import com.composables.icons.lucide.Tablets
import dev.luisamartins.medreminder.R
import dev.luisamartins.medreminder.model.DosageUnit
import dev.luisamartins.medreminder.model.Medication
import dev.luisamartins.medreminder.model.MedicationType
import dev.luisamartins.medreminder.ui.theme.MedReminderTheme

@Composable
fun MedicationListForTime(
    time: String,
    medications: List<Medication>,
    onMedicationClick: (Medication) -> Unit
) {
    Column {
        val primaryColor = MaterialTheme.colorScheme.primary
        Column(
            modifier = Modifier
                .padding(8.dp)
                .drawBehind {
                    val borderSize = 2.dp.toPx()
                    val y = size.height - borderSize / 2
                    val offset = 8f
                    drawLine(
                        color = primaryColor,
                        start = Offset(-offset, y),
                        end = Offset(size.width + offset, y),
                        strokeWidth = borderSize,
                        cap = StrokeCap.Round
                    )
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = time,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            medications.forEach { medication: Medication ->
                MedicationCard(
                    title = medication.name,
                    dosage = "${medication.dosage} ${medication.dosageUnit}",
                    remainingTime = stringResource(R.string.continuous_use),
                    icon = when (medication.type) {
                        MedicationType.PILL -> Lucide.Pill
                        MedicationType.SYRUP -> Lucide.FlaskConical
                        MedicationType.TABLET -> Lucide.Tablets
                        MedicationType.INJECTION -> Lucide.Syringe
                        MedicationType.DROPS -> Lucide.Droplets
                    },
                    checked = medication.checked,
                    onClick = {
                        onMedicationClick(medication)
                    },
                    modifier = Modifier.padding(8.dp)
                )
            }

        }
    }
}

@Preview
@Composable
private fun MedicationListForTimePreview() {
    MedReminderTheme {
        Surface {
            MedicationListForTime(
                time = "8:00",
                medications = listOf(
                    Medication(
                        id = 1,
                        name = "Omega 3",
                        dosage = 1,
                        dosageUnit = DosageUnit.Pills,
                        type = MedicationType.PILL,
                        time = "8:00",
                    ),
                    Medication(
                        id = 2,
                        name = "Vitamina D",
                        dosage = 25,
                        dosageUnit = DosageUnit.ML,
                        type = MedicationType.SYRUP,
                        time = "8:00",
                    )
                ),
                onMedicationClick = {}
            )
        }
    }
}