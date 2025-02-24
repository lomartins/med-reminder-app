package dev.luisamartins.medreminder.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Tablets
import dev.luisamartins.medreminder.ui.theme.MedReminderTheme

@Composable
fun MedicationCard(
    title: String,
    dosage: String,
    remainingTime: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        shape = MaterialTheme.shapes.large,
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onClick)
                .padding(24.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(42.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = dosage,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                remainingTime,
                modifier = Modifier.align(Alignment.Bottom)
            )
        }
    }
}


@Preview
@Composable
private fun MedicationCardPreview() {
    MedReminderTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Box(modifier = Modifier.padding(16.dp)) {
                MedicationCard(
                    title = "Omega 3",
                    dosage = "1 comprimido ao dia",
                    remainingTime = "7 dias",
                    icon = Lucide.Tablets,
                    onClick = {}
                )
            }
        }

    }
}