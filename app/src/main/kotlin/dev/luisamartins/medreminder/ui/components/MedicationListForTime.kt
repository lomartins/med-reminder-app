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
import dev.luisamartins.medreminder.ui.screens.home.HomeMedicationItemUiState
import dev.luisamartins.medreminder.ui.screens.home.MedicationTypeUiState
import dev.luisamartins.medreminder.ui.theme.MedReminderTheme

@Composable
fun MedicationListForTime(
    time: String,
    medications: List<HomeMedicationItemUiState>,
    onMedicationClick: (HomeMedicationItemUiState) -> Unit
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
            medications.forEach { medication: HomeMedicationItemUiState ->
                MedicationCard(
                    title = medication.name,
                    dosage = medication.dosage,
                    remainingTime = medication.remainingTime
                        ?: stringResource(R.string.continuous_use),
                    icon = when (medication.type) {
                        MedicationTypeUiState.PILL -> Lucide.Pill
                        MedicationTypeUiState.SYRUP -> Lucide.FlaskConical
                        MedicationTypeUiState.TABLET -> Lucide.Tablets
                        MedicationTypeUiState.INJECTION -> Lucide.Syringe
                        MedicationTypeUiState.DROPS -> Lucide.Droplets
                    },
                    checked = medication.isTaken,
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
                        type = MedicationTypeUiState.SYRUP,
                        typeDescription = "Syrup",
                        dosage = "25 ML",
                        remainingTime = null,
                        isOverdue = false,
                        isTaken = true
                    )
                ),
                onMedicationClick = {}
            )
        }
    }
}
