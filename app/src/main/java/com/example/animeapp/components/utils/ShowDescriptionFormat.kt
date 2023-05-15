package com.example.animeapp.components.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        Spacer(modifier = Modifier.height(8.dp))
    }
}