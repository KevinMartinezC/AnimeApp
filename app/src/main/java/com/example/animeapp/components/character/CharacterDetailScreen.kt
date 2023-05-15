package com.example.animeapp.components.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.R
import com.example.animeapp.components.utils.ShowDescriptionFormat
import com.example.domain.model.CharacterInfo

@Composable
fun CharacterScreen(characterDetails: CharacterInfo) {
    LazyColumn(
        modifier = Modifier.padding(bottom = 98.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = characterDetails.imageUrl),
                    contentDescription = stringResource(R.string.image_character),
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
        item {
            ShowDescriptionFormat(description = characterDetails.description)
        }
    }
}