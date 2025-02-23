package dev.luisamartins.medreminder.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.luisamartins.medreminder.R
import dev.luisamartins.medreminder.ui.components.MedicationCard
import dev.luisamartins.medreminder.ui.components.WeekDisplay
import dev.luisamartins.medreminder.ui.theme.MedReminderTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun HomeContent(
    currentDate: LocalDate,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
            .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.padding(16.dp))
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
        Column {
            MedicationCard(
                title = "Omega 3",
                dosage = "1 comprimido ao dia",
                remainingTime = "7 dias",
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun HomeContentPreview() {
    MedReminderTheme {
        HomeContent(
            currentDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        )
    }
}