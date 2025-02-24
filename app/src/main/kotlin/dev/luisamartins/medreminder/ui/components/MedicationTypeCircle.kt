package dev.luisamartins.medreminder.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Tablets
import dev.luisamartins.medreminder.ui.theme.MedReminderTheme
import kotlinx.coroutines.launch


@Composable
fun MedicationTypeCircle(
    name: String,
    icon: ImageVector,
    enabled: Boolean,
    onCLick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scaleA = remember { Animatable(initialValue = 1f) }
    val scaleB = remember { Animatable(initialValue = 1f) }
    val backgroundColor by animateColorAsState(
        targetValue = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer
    )
    val textColor by animateColorAsState(
        targetValue = if (enabled) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onPrimaryContainer
    )
    var clickEnabled by remember { mutableStateOf(true) }
    LaunchedEffect(enabled) {
        if (enabled) {
            clickEnabled = false
            val jobA = launch {
                scaleA.animateTo(
                    targetValue = 0.3f,
                    animationSpec = tween(
                        durationMillis = 50,
                    )
                )
                scaleA.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            }
            val jobB = launch {
                scaleB.animateTo(
                    targetValue = 0.9f,
                    animationSpec = tween(
                        durationMillis = 50
                    )
                )
                scaleB.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            }
            jobA.join()
            jobB.join()
        }
        clickEnabled = true
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ) {
        Surface(
            modifier = Modifier
                .size(64.dp)
                .scale(scale = scaleB.value)
                .clip(CircleShape)
                .clickable(
                    onClick = onCLick,
                    enabled = clickEnabled
                ),
            color = backgroundColor,
            shadowElevation = 2.dp,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .scale(scale = scaleA.value),
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        Text(name, style = MaterialTheme.typography.bodySmall)

    }
}

@Preview
@Composable
private fun MedicationTypeCirclePreview() {
    MedReminderTheme {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            MedicationTypeCircle(
                name = "Tablets",
                icon = Lucide.Tablets,
                enabled = false,
                onCLick = {},
                modifier = Modifier.padding(8.dp)
            )
            MedicationTypeCircle(
                name = "Tablets",
                icon = Lucide.Tablets,
                enabled = true,
                onCLick = {},
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}