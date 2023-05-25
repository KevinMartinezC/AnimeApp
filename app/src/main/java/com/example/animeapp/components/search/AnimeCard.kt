package com.example.animeapp.components.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.R
import com.example.animeapp.theme.MyApplicationTheme
import com.example.domain.model.search.Anime

private const val MAX_LINE_TEXT = 3

@Composable
fun AnimeCard(
    anime: Anime,
    onToggleFavorite: (Anime) -> Unit,
    favoriteAnime: Set<Int>,
    onAnimeSelected: (Anime) -> Unit,
    modifier: Modifier = Modifier
) {

    val isFavorite = favoriteAnime.contains(anime.id)
    
    Card(
        colors = cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape( dimensionResource(id = R.dimen.rounder_corner_4)),
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.elevation_15)),
        modifier = modifier
            .aspectRatio(3 / 4f)
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_4dp))
            .clickable {
                onAnimeSelected(anime)
            }

    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            val painter = rememberAsyncImagePainter(model = anime.imageUrl)
            if (LocalInspectionMode.current) {
                Image(
                    painter = painterResource(R.drawable.imagen1),
                    contentDescription = anime.title,
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Fit
                )
            } else {
                Image(
                    painter = painter,
                    contentDescription = anime.title,
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Fit
                )
            }
            Text(
                text = anime.title,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_4dp))
                    .weight(1f),
                textAlign = TextAlign.Center,
                maxLines = MAX_LINE_TEXT,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            IconButton(
                onClick = { onToggleFavorite(anime) },
                modifier = Modifier.weight(0.8f)

            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = stringResource(R.string.add_to_favorites),
                    tint = MaterialTheme.colorScheme.surfaceTint
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAnimeCard() {
    val anime = Anime(
        id = 1,
        title = "Demon Slayer",
        imageUrl = "https://i.blogs.es/bc1dd2/naruto/840_560.png",
    )

    MyApplicationTheme {
        AnimeCard(
            anime = anime,
            onToggleFavorite = { },
            favoriteAnime = setOf(1),
            onAnimeSelected = { },
        )
    }
}