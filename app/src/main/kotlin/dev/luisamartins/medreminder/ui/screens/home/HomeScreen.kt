package dev.luisamartins.medreminder.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    HomeContent(modifier = modifier)
}

@Serializable
object HomeScreen