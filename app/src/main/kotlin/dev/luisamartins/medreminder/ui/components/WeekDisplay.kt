package dev.luisamartins.medreminder.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.luisamartins.medreminder.R
import dev.luisamartins.medreminder.ui.theme.MedReminderTheme
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.DayOfWeek
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime

@Composable
fun WeekDisplay(
    date: LocalDate,
    modifier: Modifier = Modifier
) {
    val dayOfWeek = date.dayOfWeek.ordinal + 1
    val firstDayOfWeek = date.minus((dayOfWeek - 1).toLong(), DateTimeUnit.DAY)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (offset in 0..6) {
            val currentDay = firstDayOfWeek.plus(offset, DateTimeUnit.DAY)
            val isEnabled = currentDay == date
            WeekDay(
                weekDayName = currentDay.dayOfWeek.toWeekDayName(),
                number = currentDay.dayOfMonth,
                enabled = isEnabled
            )
        }
    }
}


@Composable
private fun WeekDay(
    weekDayName: String,
    number: Int,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    val (backgroundColor: Color, textColor: Color) = if (enabled) {
        listOf(MaterialTheme.colorScheme.primary, MaterialTheme.colorScheme.onPrimary)
    } else {
        listOf(Color.Transparent, MaterialTheme.colorScheme.onSurface)
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = weekDayName,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.size(6.dp))
        Box(
            modifier
                .size(35.dp)
                .background(backgroundColor, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "$number",
                color = textColor,
                style = MaterialTheme.typography.labelLarge,
            )

        }
    }
}

@Composable
private fun DayOfWeek.toWeekDayName(): String {
    return when (this) {
        DayOfWeek.MONDAY -> stringResource(R.string.short_weekday_monday)
        DayOfWeek.TUESDAY -> stringResource(R.string.short_weekday_tuesday)
        DayOfWeek.WEDNESDAY -> stringResource(R.string.short_weekday_wednesday)
        DayOfWeek.THURSDAY -> stringResource(R.string.short_weekday_thursday)
        DayOfWeek.FRIDAY -> stringResource(R.string.short_weekday_friday)
        DayOfWeek.SATURDAY -> stringResource(R.string.short_weekday_saturday)
        DayOfWeek.SUNDAY -> stringResource(R.string.short_weekday_sunday)
    }
}

@Preview
@Composable
private fun WeekDayPreview() {
    MedReminderTheme {
        WeekDay(
            weekDayName = "Seg",
            number = 5,
            enabled = true,
        )
    }
}

@Preview
@Composable
private fun WeekDisplayPreview() {
    MedReminderTheme {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            WeekDisplay(
                date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
            )
        }
    }
}