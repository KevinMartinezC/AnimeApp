package com.example.animeapp.components.character.utils

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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.animeapp.R
import com.example.animeapp.components.utils.ShowDescriptionFormat


@Composable
fun CharacterScreenPreviewContent(characterDetails: CharacterInfoPreview) {
    LazyColumn(
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.padding_20dp),
                end = dimensionResource(id = R.dimen.padding_20dp)
            ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_8dp))
    ) {
        item {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = characterDetails.imageUrl,
                    contentDescription = stringResource(R.string.image_character),
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.size_200))
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

data class CharacterInfoPreview(
    val imageUrl: Painter,
    val description: String
)