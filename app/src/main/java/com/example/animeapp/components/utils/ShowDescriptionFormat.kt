package com.example.animeapp.components.utils


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import org.jsoup.Jsoup

@Composable
fun ShowDescriptionFormat(description: String?) {
    description?.let {
        val cleanDescription = Jsoup.parse(description).text()
        Text(
            text = cleanDescription,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}