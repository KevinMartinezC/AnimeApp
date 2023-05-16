package com.example.animeapp.components.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.R
import com.example.animeapp.components.utils.ShowDescriptionFormat
import com.example.animeapp.components.utils.formatResourceString
import com.example.animeapp.theme.MyApplicationTheme
import com.example.domain.model.detail.AnimeCharacter
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
            if (LocalInspectionMode.current) {
                Image(
                    painter = painterResource(R.drawable.imagen1),
                    contentDescription = stringResource(R.string.image_cover),
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen.height_250dp))
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillHeight
                )
            } else {
                Image(
                    painter = rememberAsyncImagePainter(animeDetails.imageUrl),
                    contentDescription = stringResource(R.string.image_cover),
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen.height_250dp))
                        .fillMaxWidth(),
                    contentScale = ContentScale.FillHeight
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_16dp))
        ) {
            Text(
                text = animeDetails.title,
                color = MaterialTheme.colorScheme.onSurface,
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

@Preview
@Composable
fun PreviewDetailScreen() {
    val animeDetails = AnimeDetails(
        id = 1,
        imageUrl = "https://i.blogs.es/bc1dd2/naruto/840_560.png",
        title = "Anime Title",
        description = stringResource(R.string.one_piece_description),
        episodes = 24,
        averageScore = 8,
        genres = listOf("Action", "Adventure", "Fantasy"),
        englishName = "English Anime Name",
        japaneseName = "Japanese Anime Name",
        characters = listOf(
            AnimeCharacter(
                id = 1,
                name = "Character 1",
                imageUrl = "https://i.blogs.es/bc1dd2/naruto/840_560.png"
            ),
            AnimeCharacter(
                id = 2,
                name = "Character 2",
                imageUrl = "https://i.blogs.es/bc1dd2/naruto/840_560.png"
            ),
            AnimeCharacter(
                id = 3,
                name = "Character 3",
                imageUrl = "https://i.blogs.es/bc1dd2/naruto/840_560.png"
            )
        )
    )
    MyApplicationTheme {
        Box(modifier = Modifier.background(Color.Black)) {
            DetailScreen(
                animeDetails = animeDetails,
                onCharacterClick = { /* Do something here */ }
            )
        }
    }
}

