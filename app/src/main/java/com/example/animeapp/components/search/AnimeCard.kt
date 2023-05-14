package com.example.animeapp.components.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.R
import com.example.domain.model.Anime

private const val MAX_LINE_TEXT = 3

@Composable
fun AnimeCard(
    anime: Anime,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounder_corner_4)),
        elevation = CardDefaults.cardElevation(15.dp),
        modifier = modifier
            .size(
                width = dimensionResource(id = R.dimen.wight_150dp),
                height = dimensionResource(id = R.dimen.height_200dp)
            )
            .padding(dimensionResource(id = R.dimen.padding_4dp))
            .clickable {
                navController.navigate(("detail/${anime.id}"))
            }

    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            val painter = rememberAsyncImagePainter(model = anime.imageUrl)
            Image(
                painter = painter,
                contentDescription = anime.title,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = anime.title,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_2dp))
                    .weight(1f),
                textAlign = TextAlign.Center,
                maxLines = MAX_LINE_TEXT
            )
        }
    }
}