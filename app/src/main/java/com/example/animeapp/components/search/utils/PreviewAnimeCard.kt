package com.example.animeapp.components.search.utils

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.example.animeapp.R


@Composable
fun PreviewAnimeCard(
    anime: PreviewAnime,
    onToggleFavorite: (PreviewAnime) -> Unit,
    favoriteAnime: Set<Int>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val isFavorite = favoriteAnime.contains(anime.id)
    Card(
        shape = RoundedCornerShape( dimensionResource(id = R.dimen.rounder_corner_4)),
        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.elevation_15)),
        modifier = modifier
            .aspectRatio(3 / 4f)
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_4dp))
            .clickable {
                navController.navigate(("detail/${anime.id}"))
            }

    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            val painter = painterResource(id = R.drawable.imagen1)
            Image(
                painter = painter,
                contentDescription = anime.title,
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
            Text(
                text = anime.title,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_4dp))
                    .weight(1f),
                textAlign = TextAlign.Center,
                maxLines = 2,
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
                    tint = Color.Red
                )
            }
        }
    }
}

data class PreviewAnime(
    val id: Int,
    val title: String,
    val imageResId: Painter
)