package com.example.animeapp.components.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FavoriteScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(text = "Favorite")
    }
}