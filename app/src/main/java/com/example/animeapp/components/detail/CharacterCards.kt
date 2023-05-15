package com.example.animeapp.components.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.R
import com.example.domain.model.detail.AnimeCharacter

private  const val MAX_LINE_TEXT = 2

@Composable
fun CharacterCards(characters: List<AnimeCharacter>, onCharacterClick: (Int) -> Unit) {
    LazyRow {
        items(characters.size) { index ->
            val character = characters[index]
            Card(
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_8dp))
                    .width(dimensionResource(id = R.dimen.wight_120dp))
                    .height(dimensionResource(id = R.dimen.height_150dp))
                    .clickable { onCharacterClick(character.id) },
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_8dp)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(character.imageUrl),
                        contentDescription = stringResource(R.string.character_image),
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.size_64dp))
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = dimensionResource(id = R.dimen.padding_8dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = character.name,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center,
                            maxLines = MAX_LINE_TEXT,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}