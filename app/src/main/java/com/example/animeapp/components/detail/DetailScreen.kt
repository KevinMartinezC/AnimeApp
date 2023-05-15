package com.example.animeapp.components.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.R
import com.example.animeapp.components.utils.ShowDescriptionFormat
import com.example.animeapp.components.utils.formatResourceString
import com.example.domain.model.detail.AnimeDetails

private const val DEFAULT_VALUE_SCORES_EMPTY = 0

@Composable
fun DetailScreen(
    animeDetails: AnimeDetails,
    onCharacterClick: (Int) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Box {
            Image(
                painter = rememberAsyncImagePainter(animeDetails.imageUrl),
                contentDescription = stringResource(R.string.image_cover),
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.height_250dp))
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_16dp))
        ) {
            Text(
                text = animeDetails.title,
                style = MaterialTheme.typography.headlineLarge,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_8dp)))

            ShowDescriptionFormat(animeDetails.description)

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_8dp)))

            Text(
                text = formatResourceString(
                    R.string.episodes,
                    animeDetails.episodes ?: DEFAULT_VALUE_SCORES_EMPTY
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_8dp)))

            Text(
                text = formatResourceString(
                    R.string.score,
                    animeDetails.averageScore ?: DEFAULT_VALUE_SCORES_EMPTY
                ),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_8dp)))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.genders),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(end = 8.dp)
                )
                animeDetails.genres?.take(3)?.forEach { genre ->
                    Text(
                        text = genre,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_8dp)))

            Text(
                text = formatResourceString(R.string.englisname, animeDetails.englishName),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(
                modifier = Modifier.height(dimensionResource(id = R.dimen.height_4dp))
            )
            Text(
                text = formatResourceString(R.string.japanese_name, animeDetails.japaneseName),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

        }
        CharacterCards(
            characters = animeDetails.characters ?: emptyList(),
            onCharacterClick = onCharacterClick
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.height_100dp)))
    }
}
