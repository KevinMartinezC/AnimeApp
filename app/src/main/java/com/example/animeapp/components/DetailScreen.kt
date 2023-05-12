package com.example.animeapp.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailScreen(id: Int) {
    Text(text = id.toString())
}